package org.client.com.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 登录后主页
     */
    @GetMapping("/init")
    public ModelAndView init(@RequestParam("name")String name) {
        return new ModelAndView("/home/index")
                .addObject("username",name);
    }

}
