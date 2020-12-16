package com.focuscorp.DOFAN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


    @RequestMapping("/displayUsers")
    public String users() {
        return "users/displayUsers";
    }
}
