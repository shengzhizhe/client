package org.client.com.perorder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/order");
    }
}
