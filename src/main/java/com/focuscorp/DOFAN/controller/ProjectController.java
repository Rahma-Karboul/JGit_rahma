package com.focuscorp.DOFAN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {


    @RequestMapping("/displayProjects")
    public String projects() {
        return "projects/displayProjects";
    }
}
