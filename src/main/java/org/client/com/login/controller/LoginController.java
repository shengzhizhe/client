package org.client.com.login.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.client.com.MyUsernamePasswordToken;
import org.client.com.api.AccountInterface;
import org.client.com.api.TokenInterface;
import org.client.com.api.model.TokenModel;
import org.client.com.login.model.LoginModel;
import org.client.com.util.base64.Base64Util;
import org.client.com.util.redirect.RedirectUtil;
import org.client.com.util.resultJson.ResponseResult;
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
            long times = System.currentTimeMillis() + (1000 * 60 * 15);
            TokenModel tokenModel = new TokenModel();
            tokenModel.setToken(GetUuid.getUUID());
            tokenModel.setIsUse("N");
            tokenModel.setEndTimes(times);
            tokenModel.setAccount(model.getUsername());
            tokenModel.setUuid(GetUuid.getUUID());
            ResponseResult<TokenModel> result1 = tkInterface.add(tokenModel);
            if (result1.isSuccess()) {
                Cookie cookie = new Cookie("token", tokenModel.getToken());
                cookie.setPath("/");
                cookie.setMaxAge(60);
                response.addCookie(cookie);
                return new ModelAndView(redirectUtil.getRedirect() + "/home/init");
            } else {
                response.setHeader("message", "令牌出错");
                return new ModelAndView(redirectUtil.getRedirect() + "/index");
            }
        } catch (Exception e) {
            log.info("获取令牌失败");
            log.info(e.getMessage());
            response.setHeader("message", "账号或密码错误");
            return new ModelAndView(redirectUtil.getRedirect() + "/index");
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response) {
        RedirectUtil redirectUtil = new RedirectUtil();
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
                ResponseResult<TokenModel> result = tkInterface.updateToken(token_str);
                Cookie cookie = new Cookie("token", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
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
        return new ModelAndView(redirectUtil.getRedirect() + "/index");
    }

}
