package com.scm.org.paritosh.services;
import com.scm.org.paritosh.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String userid);
    Optional<User> updateUser(User user);
    void deleteUser(String userid);
    boolean isUserExist(String userid);
    boolean isUserExistByEmail(String mail);
    List<User> getAllUser();
    User getUserNameByemail(String email);
    User getEmailByUsername(String username);
    String findUseridByUsername(String username);

}
