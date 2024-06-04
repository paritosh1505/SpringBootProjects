package com.scm.org.paritosh.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")
public class UserController  {
    @Autowired
   
    //dashboard
    @PostMapping("/dashboard")
    public String userDahsBoard() {
        return "user/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String userDashBoard() {
        return "user/dashboard";
    }
    
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }
    @PostMapping("/profile")
    public String userProfilePost() {
        return "user/profile";
    }
}
