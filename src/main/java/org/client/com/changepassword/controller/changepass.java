package org.client.com.changepassword.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/changepass")
public class changepass {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/password");
    }
}
