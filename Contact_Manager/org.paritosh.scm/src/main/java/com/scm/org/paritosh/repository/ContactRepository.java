package com.scm.org.paritosh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.org.paritosh.entity.Contact;
import com.scm.org.paritosh.entity.User;

@Repository
public interface ContactRepository extends  JpaRepository<Contact,String> {
    //custom finder method
    Page<Contact> findByUser(User user,Pageable pageval);

    //custom query method
    @Query("Select c from Contact c where c.user.Id=:userId")
    List<Contact> findByUserId(@Param("userId") String userid);


}
