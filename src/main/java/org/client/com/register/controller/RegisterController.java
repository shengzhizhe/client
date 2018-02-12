package org.client.com.register.controller;

import feign.FeignException;
import org.apache.shiro.SecurityUtils;
import org.client.com.api.AccountInterface;
import org.client.com.api.model.AccountModel;
import org.client.com.register.model.RegisterModel;
import org.client.com.util.redirect.RedirectUtil;
import org.client.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private AccountInterface anInterface;
    @Autowired
    private ResponseResult result;

    @RequestMapping(value = "/toRegister")
    public ModelAndView init() {
        return new ModelAndView("/register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("form") RegisterModel model,
                                 BindingResult bindingResult) {
        try {
            SecurityUtils.getSubject().getSession().setAttribute("message", "");
            RedirectUtil redirectUtil = new RedirectUtil();
            //数据验证
            if (bindingResult.hasErrors()) {
                SecurityUtils.getSubject().getSession().setAttribute("message", bindingResult.getFieldError().getDefaultMessage());
                return new ModelAndView(redirectUtil.getRedirect() + "/register/toRegister");
            }
//两次输入的密码是否一至
            if (!model.isPass()) {
                SecurityUtils.getSubject().getSession().setAttribute("message", "两次输入的密码不一致");
                return new ModelAndView(redirectUtil.getRedirect() + "/register/toRegister");
            }

            AccountModel accountModel = new AccountModel();
            accountModel.setAccount(model.getAccount());
            accountModel.setAcctype(1);
            accountModel.setPassword(model.getPassword());
            accountModel.setTimes(System.currentTimeMillis());

            ResponseResult result = anInterface.register(accountModel);
            if (result.isSuccess())
                return new ModelAndView(redirectUtil.getRedirect() + "/register/registerOK");
            else {
                SecurityUtils.getSubject().getSession().setAttribute("message",
                        result.getCode() == 501 ? "该账户已注册" : result.getMessage());
                return new ModelAndView(redirectUtil.getRedirect() + "/register/toRegister");
            }
        } catch (FeignException f) {
            return new ModelAndView("").addObject("服务断开");
        }
    }

    @RequestMapping(value = "/registerOK", method = RequestMethod.GET)
    public ModelAndView registerOK() {
        return new ModelAndView("/registerOK");
    }
}
