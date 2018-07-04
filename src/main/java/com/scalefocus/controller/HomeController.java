package com.scalefocus.controller;

import com.scalefocus.storage.UserStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;

import static com.scalefocus.controller.UserController.USER_ID_COOKIE;

@Controller
public class HomeController {

    UserStorage users;

    public HomeController(UserStorage storage) {
        this.users = storage;
    }

    @GetMapping("/")
    public String index(
            @CookieValue(value = USER_ID_COOKIE, required = false) Cookie userId
    ) {
        if ( userId == null || !users.getUser(userId.getValue()).isPresent() ) {
            return "redirect:/users/create";
        } else {
            return "redirect:/channels/0";
        }
    }

}
