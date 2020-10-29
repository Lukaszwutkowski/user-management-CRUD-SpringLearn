package com.sda.userSda.dao;

import com.sda.userSda.model.User;
import com.sda.userSda.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserDaoJdbcImpl {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<User> getAllUsers() {
       String query = "select * from uzytkownicy";
        return getUsers(query);
    }

    //    public List<User> getAllUsers() {
    //        String query = "select * from uzytkownicy";
    //        return jdbcTemplate.queryForList(query, User.class);
    //    }

    private List<User> getUsers(String query) {
        return jdbcTemplate.query(query, (rs, i) -> {
            User user = new User();
            user.setUserId(rs.getInt("IDENTYFIKATOR"));
            user.setFirstName(rs.getString("IMIE"));
            user.setLastName(rs.getString("NAZWISKO"));
            LocalDate birthDate = rs.getDate("DATA_URODZENIA").toLocalDate();
            user.setBirthDate(birthDate);
            return user;
        });
    }


    public User getUserById(int userId) {
        String query = "select * from uzytkownicy where identyfikator = " + userId;
        List<User> users = getUsers(query);
        // return users.isEmpty() ? new User() : users.get(0);
        return users.stream().findFirst().orElse(new User());
    }

    public User getUserById1(int userId) {
        String query = "select * from uzytkownicy where indentyfikator = " + userId;
        return jdbcTemplate.queryForObject(query, User.class);
    }


    public User addUser(User user) {
        return null;
    }


    public User removeUser(User user) {
        return null;
    }


    public User modifyUser(int userId, User user) {
        return null;
    }


    public List<User> getByFirstName(String firstName) {
        String query = "select * from uzytkownicy where lower(imie) like '%" + firstName + "%'";
        return getUsers(query);
    }

    public List<User> getByLastName(String lastName) {
        String query = "select * from uzytkownicy where lower(nazwisko) like '%" + lastName + "%'";
        return getUsers(query);
    }


    public List<User> getByAgeBetween(int min, int max) {
        LocalDate minDate = LocalDate.now().minusYears(max);
        LocalDate maxDate = LocalDate.now().minusYears(min);
        String query = "select * from uzytkownicy where data_urodzenia >= '"
                + minDate.format(Utils.formatter) + "' and data_urodzenia <= '"
                + maxDate.format(Utils.formatter) + "'";
        System.out.println(query);
        return getUsers(query);
    }
}
