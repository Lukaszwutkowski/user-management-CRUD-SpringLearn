package com.sda.userSda.service;

import com.sda.userSda.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int userId);
    User removeUser(User user);
    User modifyUser(int userId, User user);
    User addUser(User user);
    List<User> getByFirstName(String firstName);
    List<User> getByLastName(String lastName);
    List<User> findByFirstNames(List<String> firstNames);
    List<User> getByAgeBetween(int minDate, int maxDate);

}
