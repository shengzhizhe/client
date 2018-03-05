package org.client.com.introduction.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/introduction")
public class IntroductionController {
    @RequestMapping("/init")
    public ModelAndView init(@RequestParam("name")String name){
        return new ModelAndView("/home/introduction")
                .addObject("username",name);
    }
}
