package com.sda.userSda.service;

import com.sda.userSda.dao.UserDao;
import com.sda.userSda.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @Override
    public User getById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User removeUser(User user) {
       return userDao.removeUser(user);
    }

    @Override
    public User modifyUser(int userId, User user) {
       return userDao.modifyUser(userId, user);
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return getAll()
                .stream()
                .filter(x -> x.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return getAll()
                .stream()
                .filter(x -> x.getFirstName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
