package org.client.com.perinformation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/perinformation")
public class InformationController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/information");
    }
}
