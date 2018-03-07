package org.client.com.setpay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/setpay")
public class SetpayController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/setpay");
    }
}
