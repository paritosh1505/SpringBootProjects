package com.scm.org.paritosh.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.org.paritosh.services.UserService;
import com.scm.org.paritosh.utils.AuthenticatedUserName;

@ControllerAdvice
public class RootController {
    @Autowired
    public UserService userService;
    @ModelAttribute
    public void GetUserNameAfterLogin(Model model, Authentication authentication){
        if(authentication==null){
            return;
        }
        String username =  AuthenticatedUserName.findUserIdusingAuth(authentication);
        System.out.println("username is "+username);
        model.addAttribute("loggedInUser", username);
        

        
        
    }
}
