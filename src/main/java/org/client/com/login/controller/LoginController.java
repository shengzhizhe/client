package org.client.com.login.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.client.com.MyUsernamePasswordToken;
import org.client.com.api.AccountInterface;
import org.client.com.api.TokenInterface;
import org.client.com.login.model.LoginModel;
import org.client.com.util.base64.Base64Util;
import org.client.com.util.redirect.RedirectUtil;
import org.client.com.util.uuidUtil.GetUuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * login
 */
@RestController
public class LoginController {
    private final static Logger log = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private AccountInterface loginInterface;
    @Autowired
    private TokenInterface tkInterface;

    /**
     * @param model  LoginModel
     * @param result BindingResult
     * @return ModelAndView
     */
    @PostMapping("/login")
    public ModelAndView loginIn(
            @Valid @ModelAttribute("form") LoginModel model,
            BindingResult result,
            HttpServletResponse response) throws Exception {
        RedirectUtil redirectUtil = new RedirectUtil();
        //验证用户和令牌的有效性
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(model.getUsername(),
//此处决定此方法只能用于普通用户
                "user",
                Base64Util.encode(model.getPassword()));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("获取令牌成功");
//        生成新的token
//        账号，新uuid（密钥），过期时间，所有者
            String account = model.getUsername();
            String tokens = GetUuid.getUUID();
            String author = "The survival of the dead";
            String type = "user";
            long times = System.currentTimeMillis() + (1000 * 60 * 15);
//单独加密
            tokens = Base64Util.encode(tokens);
            account = Base64Util.encode(account);
            author = Base64Util.encode(author);
            type = Base64Util.encode(type);
            String endTimes = Base64Util.encode(times + "");

            String token_str = tokens + "_" + account + "_" + author + "_" + type + "_" + endTimes;
//            令牌存入数据库
//            TokenModel tokenModel = new TokenModel();
//            tokenModel.setAccount(account);
//            tokenModel.setEndTimes(System.currentTimeMillis() + (1000 * 60 * 15));
//            tokenModel.setIsUse("Y");
//            tokenModel.setToken(tokens);
//            ResponseResult<TokenModel> result1 = tkInterface.add(tokenModel);
//            if (!result1.isSuccess()) {
//                response.setHeader("message", "令牌出错");
//                return new ModelAndView(redirectUtil.getRedirect() + "/index");
//            }
            Cookie cookie = new Cookie("token", token_str);
            cookie.setPath("/");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            return new ModelAndView(redirectUtil.getRedirect() + "/home/init");
        } catch (Exception e) {
            log.info("获取令牌失败");
            log.info(e.getMessage());
            response.setHeader("message", "账号或密码错误");
            return new ModelAndView(redirectUtil.getRedirect() + "/index");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            Cookie[] cookies = httpServletRequest.getCookies();
            String token_str = "";
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    token_str = cookies[i].getValue();
                    continue;
                }
            }
            if (token_str != null && !token_str.isEmpty()) {
                String[] split = token_str.split("_");
                split[split.length - 1] = Base64Util.encode(System.currentTimeMillis() + "");
                Cookie cookie = new Cookie("token", StringUtils.join(split, "_"));
                cookie.setPath("/");
                cookie.setMaxAge(60);
                response.addCookie(cookie);
            }
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
                if (log.isDebugEnabled()) {
                    log.debug("用户退出登录");
                }
            }
        } catch (SessionException e) {
            e.printStackTrace();
        }
    }

    // 跳转忘记密码
    @GetMapping("/toForgot")
    public ModelAndView toForgot() {
        return new ModelAndView("/pwd").addObject("xgmmModel", new LoginModel());
    }

    //密码找回
    @PostMapping("/forgot")
    public ModelAndView forgot(@Valid @ModelAttribute("xgmmModel") LoginModel model,
                               BindingResult result) {
        // 表单验证
        if (result.hasErrors()) {
            return new ModelAndView("/pwd").addObject("xgmmModel", model)
                    .addObject("errortextzhmm",
                            result.getFieldError().getDefaultMessage());
        }

        return null;
    }

}
