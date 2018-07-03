package com.scalefocus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.scalefocus.controller.UserController.SESSION_ID_DEFAULT;
import static com.scalefocus.controller.UserController.SESSION_ID_KEY;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(
            @CookieValue(value = SESSION_ID_KEY, defaultValue = SESSION_ID_DEFAULT) String sessionId
    ) {
        System.out.println(sessionId);
        if ( sessionId.equals(SESSION_ID_DEFAULT) ) {
            return "redirect:/users/create";
        } else {
            return "redirect:/channel";
        }
    }

}
