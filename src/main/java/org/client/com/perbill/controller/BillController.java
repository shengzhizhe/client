package org.client.com.perbill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bill")
public class BillController {
    @RequestMapping("/init")
    public ModelAndView inti(){
        return new ModelAndView("/person/bill");
    }
}
