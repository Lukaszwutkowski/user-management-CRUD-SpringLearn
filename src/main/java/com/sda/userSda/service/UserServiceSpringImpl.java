package com.sda.userSda.service;

import com.sda.userSda.dao.UserDaoSpring;
import com.sda.userSda.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
@Profile("dev")
public class UserServiceSpringImpl implements UserService{

    private UserDaoSpring userDaoSpring;

    public UserServiceSpringImpl(UserDaoSpring userDaoSpring) {
        this.userDaoSpring = userDaoSpring;
    }

    @PostConstruct
    public void init() {
        createUsers();
    }

    @Override
    public List<User> getAll() {
        System.out.println("Pobieram wszystko klasa");
        return userDaoSpring.findAll();
    }

    @Override
    public User getById(int userId) {
        return userDaoSpring.findById(userId).orElse(new User());
    }

    @Override
    public User removeUser(User user) {
        userDaoSpring.delete(user);
        return user;
    }

    @Override
    @Transactional
    public User modifyUser(int userId, User user) {
        user.setUserId(userId);
        return userDaoSpring.save(user);
    }

    @Override
    public User addUser(User user) {
        return userDaoSpring.save(user);
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return userDaoSpring.getAllByFirstName(firstName);
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return userDaoSpring.getAllByLastName(lastName);
    }

    @Override
    public List<User> findByFirstNames(List<String> firstNames) {
        return userDaoSpring.getAllByFirstNameIn(firstNames);
    }

    @Override
    public List<User> getByName(String name) {
        return userDaoSpring.getByAge(name);
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
