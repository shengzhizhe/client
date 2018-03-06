package org.client.com.pernews.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/news")
public class NewsController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/news");
    }
}
