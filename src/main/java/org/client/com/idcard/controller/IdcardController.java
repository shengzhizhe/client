package org.client.com.idcard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/idcard")
public class IdcardController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/idcard");
    }
}
