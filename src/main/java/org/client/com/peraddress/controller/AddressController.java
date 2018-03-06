package org.client.com.peraddress.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/address")
public class AddressController {
    @RequestMapping("/init")
    public ModelAndView init(){
        return new ModelAndView("/person/address");
    }
}
