package com.scm.org.paritosh.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scm.org.paritosh.entity.Contact;
import com.scm.org.paritosh.entity.User;
import com.scm.org.paritosh.repository.ContactRepository;
import com.scm.org.paritosh.services.ContactService;

@Service
public class ContactServiceImple implements ContactService {

    ContactRepository contactObject;
    ContactServiceImple(ContactRepository contRepo){
        this.contactObject= contRepo;
    }
    @Override
    public Contact saveUser(Contact data) {
        
        String contactId= UUID.randomUUID().toString();
        data.setId(contactId);
        return contactObject.save(data);
       
    }

    @Override
    public Contact updateUser(Contact contact) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public List<Contact> getAllContact() {
       return contactObject.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactObject.findById(id).orElse(null);
    }

   

    @Override
    public List<Contact> searchEntry(String email, String name) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");

    }

    @Override
    public List<Contact> getbyUserId(String userid) {
       return contactObject.findByUserId(userid);
    }
    @Override
    public Page<Contact> getByUser(User user, int page, int size) {
        var pageEntry= PageRequest.of(page, size);
       return contactObject.findByUser(user, pageEntry);
    }
   
    

}
