package org.client.com.success.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/success")
public class SuccessController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/home/success");
    }
}
