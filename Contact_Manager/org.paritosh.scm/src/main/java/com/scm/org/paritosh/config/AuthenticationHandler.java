package com.scm.org.paritosh.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.org.paritosh.entity.Provider;
import com.scm.org.paritosh.entity.User;
import com.scm.org.paritosh.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepo;
    Logger log = LoggerFactory.getLogger(AuthenticationHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                DefaultOAuth2User defaultoath = (DefaultOAuth2User)authentication.getPrincipal();
                System.out.println("***Default authencticatop"+defaultoath);
                var oauthProviderAuthentication= (OAuth2AuthenticationToken)authentication;
                User userVal  = new User();
                userVal.setId(UUID.randomUUID().toString());
                userVal.setRoles(List.of("ROLE_USER"));
                userVal.setEmailVerified(true);
                userVal.setEnabled(true);

                String ProviderId = oauthProviderAuthentication.getAuthorizedClientRegistrationId();
                String email="";
                 if(ProviderId.equalsIgnoreCase("google")){
                     email = defaultoath.getAttribute("email");
                    String name = defaultoath.getAttribute("name");
                    String profile_pic = defaultoath.getAttribute("picture");
                  
                   userVal.setEmail(email);
                   userVal.setUsername(name);
                   userVal.setProfilePic(profile_pic);
                   
                   userVal.setProvider(Provider.GOOGLE);
                   userVal.setProviderUserid(userVal.getUsername());
                   userVal.setProvider(Provider.GOOGLE);
                   userVal.setAbout("This account is created using google");
       
                 }
                 else{
                    String name = defaultoath.getAttribute("login");
                    email = defaultoath.getAttribute("email")!=null? defaultoath.getAttribute("email"): defaultoath.getAttribute("login")+"@gmail.com";
                    String profile_pic = defaultoath.getAttribute("avatar_url");
                    userVal.setEmail(email);
                    userVal.setProfilePic(profile_pic);
                    userVal.setUsername(name);
                    userVal.setProviderUserid(userVal.getUsername());
                    userVal.setProvider(Provider.GITHUB);
                    userVal.setAbout("This account is created using github");

                 }
                 User urepo= userRepo.findByEmail(email).orElse(null);
                 if(urepo==null){
                     userRepo.save(userVal);
                     log.info("User saved"+email);
                 }
                 log.info("Authentication Handler Execution");
                response.sendRedirect("/user/profile");
        
    }

}
