package org.client.com.safety.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/safety")
public class SafetyController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/safety");
    }
}
