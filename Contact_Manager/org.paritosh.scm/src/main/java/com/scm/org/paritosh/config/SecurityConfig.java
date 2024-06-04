package com.scm.org.paritosh.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import com.scm.org.paritosh.implementation.SecurityCustomUserDetailService;



@Configuration
public class SecurityConfig {
   
    //user create and login pge with java code with in memory service

  /*   public UserDetailsService userDetailsService(){
        var Userdetail_1 = User.withUsername("admin123")
        .password("admin123")
        .roles("ADMIN")
        .build();
        var Userdetail_2 = User.withUsername("admin122")
        .password("admin122")
        .roles("ADMIN")
        .build();
        var inMemoryDetail= new InMemoryUserDetailsManager(Userdetail_1,Userdetail_2);
        return inMemoryDetail;
    } */
    private SecurityCustomUserDetailService userDetailsService;
    public SecurityConfig(SecurityCustomUserDetailService suser){
        this.userDetailsService=suser;
    }
    @Autowired
    private AuthenticationHandler authenticationHandler;
    @Bean
    public AuthenticationProvider AuthenticationProvider(){
       DaoAuthenticationProvider daoprovider= new DaoAuthenticationProvider();
        daoprovider.setUserDetailsService(userDetailsService);
        daoprovider.setPasswordEncoder(passwordEncoder());
        return daoprovider;

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/test2");
            formLogin.successForwardUrl("/user/profile");
            formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            
           
        });
       
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(forlogout->{
            forlogout.logoutUrl("/oyeLogout");
            forlogout.logoutSuccessUrl("/login?logout=True");

        });
        //oauth configration
        
        httpSecurity.oauth2Login(oathlogin->{
            oathlogin.loginPage("/login");
            oathlogin.successHandler(authenticationHandler);
        });
        
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
