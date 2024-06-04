package com.scm.org.paritosh.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.scm.org.paritosh.entity.User;
import com.scm.org.paritosh.formEntry.UserEntry;
import com.scm.org.paritosh.implementation.UserServiceimple;
import com.scm.org.paritosh.utils.MessageData;
import com.scm.org.paritosh.utils.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class FirstContoller {
        
    private UserServiceimple userService;
    public FirstContoller(UserServiceimple service){
        this.userService=service;
    }
  
    @GetMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isValue", "false");
        return "about";
    }
    @GetMapping("/service")
    public String servicePage(){
        return "service";
    }
  
    @GetMapping("/contact")
    public String contactPage() {
        return "contacts";
    }
    @PostMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/login")
    public String loginPage_get(){
        return "login";
    }
    @GetMapping("/register")
    public String signupPage(Model model) {
        UserEntry userDetail = new UserEntry();
        model.addAttribute("userDetail", userDetail);
        return "register";

    }
    @GetMapping("/")
    public String GetHome(){
        return "redirect:/home";
    }

    

    @PostMapping("/doRegister")
    public String processData (@Valid @ModelAttribute("userDetail") UserEntry userEntry,BindingResult resultBinding,HttpSession sessionEntry){
        //Builder is not storng default value
      /*   User user = User.builder()
        .username(userEntry.getUsername())
        .email(userEntry.getEmail())
        .password(userEntry.getPassword())
        .about(userEntry.getAbout())
        .phoneNumber(userEntry.getPhoneNumber())
        .build(); */
        if(resultBinding.hasErrors()){
            return "register";
        }
        User user = new User();
        user.setUsername(userEntry.getUsername());
        user.setEmail(userEntry.getEmail());
        user.setPassword(userEntry.getPassword());
        user.setAbout(userEntry.getAbout());
        user.setPhoneNumber(userEntry.getPhoneNumber());
        userService.saveUser(user);
        MessageData msg = new MessageData();
        msg.setType("Registration Successfull");
        msg.setContent(MessageType.green);
        //sessionEntry.setAttribute("messageAfterSignup", "Registration Sucessfull");
        sessionEntry.setAttribute("messageAfterSignup", msg);
        return "redirect:/register";
    }
    
    
}
