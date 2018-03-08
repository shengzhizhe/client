package org.client.com.api.controller;

import org.client.com.util.resultJson.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class Login2Controller {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseResult login(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        ResponseResult result = new ResponseResult();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            result.setSuccess(false);
            result.setMessage("账户或密码不能为空");
        } else {
            if (username.equals("admin") && password.equals("admin")) {
                result.setSuccess(true);
            } else {
                result.setSuccess(false);
                result.setMessage("账户或密码错误");
            }
        }
        return result;
    }
}
