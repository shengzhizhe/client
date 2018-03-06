package org.client.com.coupon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @RequestMapping("/init")
    public ModelAndView init(@RequestParam("name")String name){
        return new ModelAndView("/person/coupon")
                .addObject("username",name);
    }
}
