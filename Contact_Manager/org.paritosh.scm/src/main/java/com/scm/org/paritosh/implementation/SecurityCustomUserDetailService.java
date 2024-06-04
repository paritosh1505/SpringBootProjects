package com.scm.org.paritosh.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.org.paritosh.repository.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    public SecurityCustomUserDetailService( UserRepository urepo){
        this.userRepository=urepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Username Not Found"+username));
    }

}
