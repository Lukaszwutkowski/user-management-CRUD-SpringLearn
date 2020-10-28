package com.sda.userSda.dao;

import com.sda.userSda.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class UserDaoH2Impl implements UserDao {

    private EntityManager em;

    public UserDaoH2Impl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> getAllUsers() {
       return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(int userId) {
        return em.find(User.class, userId);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        em.persist(user);
        return getUserById(user.getUserId());
    }

    @Override
    @Transactional
    public User removeUser(User user) {
        User userToRemove = getUserById(user.getUserId());
        em.remove(userToRemove);
        return user;
    }

    @Override
    @Transactional
    public User modifyUser(int userId, User user) {
        User newUser = getUserById(userId);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setBirthDate(user.getBirthDate());
        em.persist(newUser);
        return user;
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return em
                .createQuery("select u from User u where lower(u.firstName) like '%" + firstName.toLowerCase() + "%'", User.class)
                .getResultList();
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return em
                .createQuery("select u from User u where lower(u.lastName) like '%" + lastName.toLowerCase() + "%'", User.class)
                .getResultList();
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
}
