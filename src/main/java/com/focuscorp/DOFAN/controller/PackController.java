package com.focuscorp.DOFAN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PackController {

    @RequestMapping("/pack")
    public String projects() {
        return "projects/pack";
    }
}
