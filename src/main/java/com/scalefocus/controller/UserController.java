package com.scalefocus.controller;

import com.scalefocus.model.User;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    public static final String SESSION_ID_KEY = "SessionId";

    UserStorage storage;

    @Autowired
    public UserController(UserStorage storage) {
        this.storage = storage;
    }

    @GetMapping("/users/create")
    public String getUserForm(Model model) {
        model.addAttribute("user", new User());
        return "CreateUser";
    }

    @PostMapping("/users/create")
    public String createUser(
            @ModelAttribute User user,
            HttpServletResponse response
    ) {
        storage.addUser(user);

        Cookie cookie = new Cookie(SESSION_ID_KEY, user.getSessionId());
        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/users/all";
    }

    @GetMapping("/users/all")
    public String getAll(Model model) {
        model.addAttribute("users", storage.getAll());
        return "ShowUsers";
    }
}