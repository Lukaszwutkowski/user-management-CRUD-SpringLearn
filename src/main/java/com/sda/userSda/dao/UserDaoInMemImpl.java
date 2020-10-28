package com.sda.userSda.dao;


import com.sda.userSda.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Profile("test")
public class UserDaoInMemImpl implements UserDao {

    private Map<Integer, User> userMap;

    public UserDaoInMemImpl() {
        userMap = new HashMap<>();
        createUsers();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User getUserById(int userId) {
        return userMap.get(userId);
    }

    @Override
    public User addUser(User user) {
        int userId = userMap.keySet().stream().mapToInt(x -> x).max().orElse(0) + 1;
        user.setUserId(userId);
        userMap.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User removeUser(User user) {
        userMap.remove(user.getUserId());
        return user;
    }

    @Override
    public User modifyUser(int userId, User user) {
        user.setUserId(userId);
        userMap.put(userId, user);
        return user;
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return getAllUsers()
                .stream()
                .filter(x -> x.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return getAllUsers()
                .stream()
                .filter(x -> x.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getByAgeBetween(int min, int max) {
        LocalDate now = LocalDate.now();
        return getAllUsers().stream()
                .filter(x -> {
                    int years = Period.between(x.getBirthDate(), now).getYears();
                    return (years >= min && years <= max);
                }).collect(Collectors.toList());
    }

    private void createUsers() {
        User u1 = new User();
        u1.setFirstName("Pawel");
        u1.setLastName("Nowak");
        u1.setBirthDate(LocalDate.of(1999, 05, 05));

        User u2 = new User();
        u2.setFirstName("Kamil");
        u2.setLastName("Baryla");
        u2.setBirthDate(LocalDate.of(1986, 05, 25));

        User u3 = new User();
        u3.setFirstName("Katarzyna");
        u3.setLastName("Andrzejewska");
        u3.setBirthDate(LocalDate.of(1989, 10, 12));

        User u4 = new User();
        u4.setFirstName("Magda");
        u4.setLastName("Kapsel");
        u4.setBirthDate(LocalDate.of(1979, 04, 23));

        User u5 = new User();
        u5.setFirstName("Piotr");
        u5.setLastName("Nicpon");
        u5.setBirthDate(LocalDate.of(1972, 06, 07));

        addUser(u1);
        addUser(u2);
        addUser(u3);
        addUser(u4);
        addUser(u5);
    }
}
