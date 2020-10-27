package com.sda.userSda.controller;

import com.sda.userSda.model.User;
import com.sda.userSda.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class ThUserController {

    private UserService userService;
    private MessageSource messageSource;

    public ThUserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String index(Model model){
        List<User> userList = userService.getAll();
        model.addAttribute("users", userList);
        System.out.println(messageSource.getMessage("usr", null, Locale.ENGLISH)); // mozna np. Locale.ENGLISH. Locale najlepiej zrobic null bo wtedy pobierz elokalizacje z komputera
        return "index";
    }

    @PostMapping("/")
    public String saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        } else {
            userService.addUser(user);
            return "redirect:/";
        }
    }

    @PostMapping("/modify")
    public String modify(@Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "/modify-user";
        }
        System.out.println(user);
        userService.modifyUser(user.getUserId(), user);
        return "redirect:/";
    }

    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = new User();
        user.setUserId(id);
        userService.removeUser(user);
        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modifyUser(@RequestParam int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "modify-user";
    }


}
