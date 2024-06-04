package com.scm.org.paritosh.repository;
import com.scm.org.paritosh.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

    Optional<User> findByEmail(String Email);
    Optional<User> findEmailByUsername(String username);

    //String findIdByUsername(String username);
    @Query("Select u.id from User u where u.username=:username")
    String findUsernameById(@Param("username") String username);
}
