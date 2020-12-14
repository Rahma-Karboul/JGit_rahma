package com.focuscorp.DOFAN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobController {

    @RequestMapping("/Jobs")
    public String jobs() {
        return "Jobs";
    }

}
