package org.client.com.email.controller;

import org.client.com.util.email.EmailUtil;
import org.client.com.util.resultJson.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * email test
 * 带有附件方法
 */
@RestController
@RequestMapping("/email")
public class EmailController {
    @Value("${spring.mail.username}")
    private String forEmail;
    @Value("${filesPath}")
    private String filesPath;

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private ResponseResult result;

    @RequestMapping(value = "/toemail")
    public ResponseResult regitEmail(@RequestParam("title") String title, @RequestParam("toUser") String toUser) {
        EmailUtil emailUtil = new EmailUtil();
        boolean b = emailUtil.toEmail(mailSender, title, "您本次验证码为<span style='color:red;'>123</span>", forEmail, toUser);
        result.setSuccess(b);
        return result;
    }

}
