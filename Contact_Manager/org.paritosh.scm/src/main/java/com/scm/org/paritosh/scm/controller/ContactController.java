package com.scm.org.paritosh.scm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.org.paritosh.entity.Contact;
import com.scm.org.paritosh.entity.User;
import com.scm.org.paritosh.formEntry.ContactForm;
import com.scm.org.paritosh.services.ContactService;
import com.scm.org.paritosh.services.UserService;
import com.scm.org.paritosh.services.imageService;
import com.scm.org.paritosh.utils.AuthenticatedUserName;
import com.scm.org.paritosh.utils.MessageData;
import com.scm.org.paritosh.utils.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/user/contact")
public class ContactController {
    
    @Autowired
    private ContactService ctctService;
    @Autowired
    private UserService userService;

    @Autowired
    private imageService imgserv;
    @GetMapping("/add")
    public String AddContact(Model model){

        ContactForm contactForm =new ContactForm();
        contactForm.setFav(true);
        model.addAttribute("contactDetail", contactForm);

        return "user/contactpage";

    }
    @PostMapping("/add")
    public String SaveContact(@Valid @ModelAttribute("contactDetail") ContactForm contactForm,BindingResult result, 
    Authentication authentication, HttpSession session) {
        if(result.hasErrors()){
            session.setAttribute("messageAfterSignup", 
            MessageData.builder().type("Plese fill detail").
            content(MessageType.red).build());
            return "user/contactPage";
        }
        String username = AuthenticatedUserName.findUserIdusingAuth(authentication);
        System.out.println("*******************************************"+contactForm.getPictureContact().getOriginalFilename());
        User userValue = userService.getEmailByUsername(username);
        String ImagweUpload = imgserv.UploadImage(contactForm.getPictureContact());
        Contact contact = new Contact();
        contact.setAddress(contactForm.getAddress());
        contact.setDesc_val(contactForm.getDescContact());
        contact.setEmail(contactForm.getEmailContact());
        contact.setPhoneno(contactForm.getPhonenoContact());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setFav(contactForm.isFav());
        contact.setName(contactForm.getContactName());
        contact.setUser(userValue);
        contact.setPicture(ImagweUpload);
        ctctService.saveUser(contact);
        session.setAttribute("messageAfterSignup", MessageData.builder().type("Contact has been added").
        content(MessageType.green).build());        
        return "redirect:/user/contact/add";
    }
 
    
    @GetMapping("")
    public String GetContact(Model model,Authentication auth) {
        String userName  = AuthenticatedUserName.findUserIdusingAuth( auth);
        System.out.println("#######################"+userName);
        String UserId = userService.findUseridByUsername(userName);
        System.out.println("***************"+UserId);
        List<Contact> ctcdetail= ctctService.getbyUserId(UserId);
        model.addAttribute("contactDetails", ctcdetail);
    
       
        return "user/contactdetail";
    }
    
}
