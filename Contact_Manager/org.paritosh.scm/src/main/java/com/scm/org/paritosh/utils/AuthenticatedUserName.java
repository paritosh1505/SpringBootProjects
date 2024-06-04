package com.scm.org.paritosh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;



public class AuthenticatedUserName {

    public static String findUserIdusingAuth(Authentication authentication){
        
      Logger logval = LoggerFactory.getLogger(AuthenticatedUserName.class);
        if(authentication instanceof OAuth2AuthenticationToken){
            var principal = (OAuth2AuthenticationToken) authentication;
            String username="";
            String providerval = principal.getAuthorizedClientRegistrationId();
            logval.info("Provider_value", providerval);
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            if(providerval.equalsIgnoreCase("google")){
                   username =  user.getAttribute("name");
            }
            else{
                username = user.getAttribute("name")!=null?user.getAttribute("name"):user.getAttribute("login");
            }
            return username;
        }
        else{
            return authentication.getName();
        }
        

    }
}
