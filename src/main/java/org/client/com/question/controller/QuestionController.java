package org.client.com.question.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/question");
    }
}
