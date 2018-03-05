package org.client.com.sreach.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 列表页面
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/init")
    public ModelAndView init(@RequestParam("name")String username){
        return new ModelAndView("/home/search")
                .addObject("username",username);
    }
}
