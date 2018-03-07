package org.client.com.changeemail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/changeemail")
public class ChangeEmailController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/email");
    }
}
