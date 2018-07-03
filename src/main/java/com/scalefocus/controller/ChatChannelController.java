package com.scalefocus.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ChatChannelController {
    @GetMapping("/channel")
    public String chatChannel(Model model) {
        model.addAttribute("messages", getChatMessage());

        return "ChatMessage";
    }
}
