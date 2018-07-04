package com.scalefocus.controller;

import com.scalefocus.model.ChatChannel;
import com.scalefocus.model.ChatMessage;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        users.getUser(sessionId).ifPresent((user) -> channel.addMessage(new ChatMessage(user, message)));
        
        return "redirect:/channel";
    }

    @PostMapping("/msgDelete")
    public String deleteMessage(@RequestParam("msgId") long messageId) {
        channel.getMessageById(messageId).ifPresent(channel::deleteMessage);
        return "redirect:/channel";
    }

    @PostMapping("/msgEdit")
    public String editMessage(@RequestParam("msgId") long messageId,
                              @RequestParam("msg") String newMessage) {
        channel.getMessageById(messageId).ifPresent(msg -> msg.setMessage(newMessage));
        return "redirect:/channel";
    }

    @PostMapping("/msgEditForm")
    public String editMessageForm(@RequestParam("msgId") long messageId,
                                  @RequestParam("msg") String oldMessage,
                                  Model model) {
        model.addAttribute("msgId", messageId);
        model.addAttribute("msg", oldMessage);

        return "EditMessage";
    }
}
