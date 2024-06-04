package com.scm.org.paritosh.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Component
public class SessionHandler  {
    public static void removeMessage(){
        try{
            ServletRequestAttributes attribute = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if(attribute!=null){
                HttpServletRequest request = attribute.getRequest();
                HttpSession sessionVal = request.getSession();        
                sessionVal.removeAttribute("messageAfterSignup");
            }
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    
    }

}
