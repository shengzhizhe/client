package org.client.com.perchange.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/change")
public class ChangeController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/change");
    }
}
