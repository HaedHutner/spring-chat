package com.scalefocus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;

import static com.scalefocus.controller.UserController.SESSION_ID_KEY;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(
            @CookieValue(value = SESSION_ID_KEY, required = false) Cookie sessionId
    ) {
        if ( sessionId == null ) {
            return "redirect:/users/create";
        } else {
            return "redirect:/channel";
        }
    }

}
