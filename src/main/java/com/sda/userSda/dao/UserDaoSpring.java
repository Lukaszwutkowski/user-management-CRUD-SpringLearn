package com.sda.userSda.dao;

import com.sda.userSda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDaoSpring extends JpaRepository<User, Integer> {
    List<User> getAllByFirstName(String firstName);
    List<User> getAllByLastName(String lastName);
    @Query(name = "dateBetween")
    List<User> getByAge(@Param("name") String param);
    List<User> getAllByFirstNameIn(List<String> users);



   // List<User> getAllByFirstNameAndLastName(String firstName, String lastName);

   // List<User> getAllByFirstNameIn(List<String> firstName);
}
