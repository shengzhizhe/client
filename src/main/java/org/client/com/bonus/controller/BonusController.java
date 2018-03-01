package org.client.com.bonus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bonus")
public class BonusController {
    @GetMapping("init")
    public ModelAndView init(@RequestParam("name")String name){
        return new ModelAndView("/person/bonus")
                .addObject("username",name);
    }
}
