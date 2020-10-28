package com.sda.userSda.dao;

import com.sda.userSda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jdbc")
public class UserDaoJpaImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
       String query = "select * from uzytkownicy";
        return jdbcTemplate.query(query, (rs, i) -> {
            User user = new User();
            user.setUserId(rs.getInt("id"));
            return user;
        });
    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User removeUser(User user) {
        return null;
    }

    @Override
    public User modifyUser(int userId, User user) {
        return null;
    }

    @Override
    public List<User> getByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<User> getByLastName(String lastName) {
        return null;
    }

    @Override
    public List<User> getByAgeBetween(int min, int max) {
        return null;
    }
}
