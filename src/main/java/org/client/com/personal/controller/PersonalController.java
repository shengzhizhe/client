package org.client.com.personal.controller;

import org.client.com.home.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    private final static Logger logger = LoggerFactory.getLogger(PersonalController.class);

    @GetMapping("/init")
    public ModelAndView init(@RequestParam("name")String name){
        return new ModelAndView("/person/index")
                .addObject("username",name);
    }
}
