package com.focuscorp.DOFAN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobController {

    @RequestMapping("/jobs")
    public String jobs() {
        return "/jobs/jobs";
    }


    @RequestMapping("/addJob")
    public String addJob() {
        return "/jobs/addJob";
    }

}
