package com.scm.org.paritosh.implementation;



import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.org.paritosh.entity.User;
import com.scm.org.paritosh.repository.UserRepository;
import com.scm.org.paritosh.services.UserService;
import com.scm.org.paritosh.utils.ResourceNotFoundException;

@Service
public class UserServiceimple implements UserService {
    
    private UserRepository RepoVal;
    private PasswordEncoder passwordEncoder;
    public UserServiceimple(PasswordEncoder pwdEncoder,UserRepository repo){
        this.passwordEncoder=pwdEncoder;
        this.RepoVal=repo;
    }
    
    @Override
    public User saveUser(User user) {
        String uid= UUID.randomUUID().toString();
        user.setId(uid);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set the user role
        
        user.setRoles(List.of("ROLE_USER"));
        return RepoVal.save(user);
    }

    @Override
    public Optional<User> getUserById(String userid) {
        
       return RepoVal.findById(userid);
    }

    @Override
    public Optional<User> updateUser(User user) {
        
       User exstinguser= RepoVal.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("USer not found"));
       exstinguser.setUsername(user.getUsername());
       exstinguser.setPassword(user.getPassword());
       exstinguser.setAbout(user.getAbout());
       exstinguser.setEmail(user.getEmail());
       exstinguser.setEmailVerified(user.isEmailVerified());
       exstinguser.setPhoneVerified(user.isPhoneVerified());
       exstinguser.setProvider(user.getProvider());
       exstinguser.setProviderUserid(user.getProviderUserid());
       exstinguser.setEnabled(user.isEnabled());
       exstinguser.setProfilePic(user.getProfilePic());

       User saveDetail = RepoVal.save(exstinguser);
       return Optional.ofNullable(saveDetail);

    }

    @Override
    public void deleteUser(String userid) {
        User user = RepoVal.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User id not found"));
        RepoVal.delete(user);
    }

    @Override
    public boolean isUserExist(String userid) {
        
        User user = RepoVal.findById(userid).orElse(null);
        return user!=null ? true:false;
    }

    @Override
    public boolean isUserExistByEmail(String emailname) {
       
        User user = RepoVal.findByEmail(emailname).orElse(null);
        return user!=null ?true:false;
    }

    @Override
    public List<User> getAllUser() {
        return RepoVal.findAll();
    }

    @Override
    public User getUserNameByemail(String email) {
        return RepoVal.findByEmail(email).orElse(null);
    }

    @Override
    public User getEmailByUsername(String username) {
        return RepoVal.findEmailByUsername(username).orElse(null);
    }

    @Override
    public String findUseridByUsername(String username) {
       return RepoVal.findUsernameById(username);
    }

}
