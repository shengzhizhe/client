package org.client.com.pay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/pay")
public class PayController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/home/pay");
    }
}
