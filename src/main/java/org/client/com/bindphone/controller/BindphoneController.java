package org.client.com.bindphone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bindphone")
public class BindphoneController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/bindphone");
    }
}
