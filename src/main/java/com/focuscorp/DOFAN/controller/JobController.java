package com.focuscorp.DOFAN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@Controller
public class JobController {

    @RequestMapping("/Jobs")
    public String jobs() {
        return "Jobs";
    }

}
