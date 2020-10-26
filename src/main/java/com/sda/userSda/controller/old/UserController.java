package com.sda.userSda.controller.old;

import com.sda.userSda.model.User;
import com.sda.userSda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET) // http://localhost:8080/users
    public List<User> hello() {
        System.out.println("Pobieram wszystkich uzytkownikow");
        return userService.getAll();
    }

    @GetMapping("/user/id/{id}") // http://localhost:8080/user/id/2
    public User getUser(@PathVariable int id) {
        System.out.println("Pobieram uzytkownika o id " + id);
        return userService.getById(id);
    }

    @GetMapping("/user/firstName") // http://localhost:8080/user/firstName?firstName=Kamil
    public List<User> getByFirstName(@RequestParam String firstName) {
        System.out.println("Pobieram uzytkownika o imieniu/imionach " + firstName);
        return userService.getByFirstName(firstName);
    }

    @GetMapping("/user/lastName") // http://localhost:8080/user/lastName?lastName=Nowak
    public List<User> getByLastName(@RequestParam String lastName) {
        System.out.println("Pobieram uzytkownika o nawisku " + lastName);
        return userService.getByLastName(lastName);
    }

    @DeleteMapping("/user/delete/{id}")
    public User deleteUser(@PathVariable int id) { // W PRAWDZIWYM PROGRAMIE ZAMIAST STRING POWINIEN BYC USER
        User user = userService.getById(id);
        System.out.println("Kasuje uzytkownika " + user);
        userService.removeUser(user);
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) { // W PRAWDZIWYM PROGRAMIE ZAMIAST STRING POWINIEN BYC USER
        System.out.println("Dodaje uzytkownika " + user);
        User newUser = userService.addUser(user);
        return newUser;
    }

    @PutMapping("/user/modify")
    public User modifyUser(@RequestParam int id, @RequestBody User user) { // W PRAWDZIWYM PROGRAMIE ZAMIAST STRING POWINIEN BYC USER
        User newUser = userService.modifyUser(id, user);
        System.out.println("Modyfikuje uzytkownika " + newUser);
        return newUser;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*
    @RequestMapping("/")
    public String hello() {
        return "Witaj swiecie";
    }
    */
}
