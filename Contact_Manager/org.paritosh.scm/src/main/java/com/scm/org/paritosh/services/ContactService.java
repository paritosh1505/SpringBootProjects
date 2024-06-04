package com.scm.org.paritosh.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.org.paritosh.entity.Contact;
import com.scm.org.paritosh.entity.User;

public interface ContactService {
    Contact saveUser(Contact contact);
    Contact updateUser(Contact contact);
    List<Contact> getAllContact();
    Contact getById(String id);
    //void deleteContact(String id);
    List<Contact> searchEntry(String email,String name);
    List<Contact> getbyUserId(String userid);
    Page<Contact> getByUser(User user,int page,int size );
    
}
