package com.scalefocus.controller;

import com.scalefocus.model.ChatChannel;
import com.scalefocus.model.ChatMessage;
import com.scalefocus.model.User;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Controller
public class ChatChannelController {

    private UserStorage users;
    private ChatChannel channel;

    @Autowired
    private ChatChannelController(UserStorage storage, ChatChannel chatChannel) {
        this.users = storage;
        this.channel = chatChannel;
    }

    @GetMapping("/channel")
    public String chatChannel(Model model) {

        model.addAttribute("messages", channel.getChatMessages());

        return "ChatMessage";
    }

    @PostMapping("/channel")
    public String postMessage(
            @RequestParam("msg") String message,
            @CookieValue(UserController.SESSION_ID_KEY) String sessionId
    ) {
        users.getUser(sessionId).ifPresent((user) -> {
            channel.addMessage(new ChatMessage(user, message));
        });

        return "redirect:/channel";
    }
}
