package com.scalefocus.controller;

import com.scalefocus.storage.UserStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

import static com.scalefocus.controller.UserController.SESSION_ID_KEY;

@Controller
public class HomeController {

    UserStorage users;

    public HomeController(UserStorage storage) {
        this.users = storage;
    }

    @RequestMapping("/")
    public String home(
            @CookieValue(value = SESSION_ID_KEY, required = false) Cookie sessionId
    ) {
        if ( sessionId == null || !users.getUser(sessionId.getValue()).isPresent() ) {
            return "redirect:/users/create";
        } else {
            return "redirect:/channel";
        }
    }

}
