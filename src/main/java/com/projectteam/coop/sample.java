package com.projectteam.coop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class sample {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sample() {
        return "index";
    }
}
