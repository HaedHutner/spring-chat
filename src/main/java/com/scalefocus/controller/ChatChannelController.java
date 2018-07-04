package com.scalefocus.controller;

import com.scalefocus.model.ChatChannel;
import com.scalefocus.model.ChatMessage;
import com.scalefocus.storage.ChatChannelStorage;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class ChatChannelController {

    private UserStorage users;
    private ChatChannelStorage channels;

    private static final String CHAT_CHANNEL_COOKIE = "ChatChannel";

    @Autowired
    private ChatChannelController(UserStorage storage, ChatChannelStorage chatChannel) {
        this.users = storage;
        this.channels = chatChannel;
    }

    @GetMapping("/channel")
    public String chatChannel(
            @CookieValue(value = CHAT_CHANNEL_COOKIE, required = false) Cookie channelId,
            Model model,
            HttpServletResponse response
    ) {

        if (channelId == null) {
            Cookie cookie = new Cookie(CHAT_CHANNEL_COOKIE, ChatChannelStorage.DEFAULT_CHANNEL_ID.toString());
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            channels.get(Integer.parseInt(channelId.getValue())).ifPresent(channel -> {
                model.addAttribute("messages", channel.getChatMessages());
                model.addAttribute("channelTitle", channel.getTitle());
                model.addAttribute("channels", channels.getAll());
            });
        }

        return "ChatMessage";
    }

    @PostMapping("/channel")
    public String postMessage(
            @RequestParam("msg") String message,
            @CookieValue(UserController.SESSION_ID_KEY) String sessionId,
            @CookieValue(CHAT_CHANNEL_COOKIE) int channelId
    ) {
        users.getUser(sessionId).ifPresent((user) -> {
            channels.get(channelId).ifPresent(channel -> {
                channel.addMessage(new ChatMessage(user, message));
            });
        });

        return "redirect:/channel";
    }

    @PostMapping("/msgDelete")
    public String deleteMessage(
            @RequestParam("msgId") long messageId,
            @CookieValue(CHAT_CHANNEL_COOKIE) int channelId
    ) {
        channels.get(channelId).ifPresent(channel -> {
            channel.getMessageById(messageId).ifPresent(channel::deleteMessage);
        });
        return "redirect:/channel";
    }

    @PostMapping("/msgEdit")
    public String editMessage(@RequestParam("msgId") long messageId,
                              @RequestParam("msg") String newMessage,
                              @CookieValue(CHAT_CHANNEL_COOKIE) int channelId
    ) {
        channels.get(channelId).ifPresent(channel -> {
            channel.getMessageById(messageId).ifPresent(msg -> msg.setMessage(newMessage));
        });
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

    @PostMapping("/newChannel")
    public String createChannel(
            @RequestParam("channelName") String channelName,
            Model model
    ) {

        channels.register(new ChatChannel(channelName));

        return "redirect:/channel";
    }

    @GetMapping("/newChannel")
    public String getNewChannelForm() {
        return "CreateChannel";
    }

    @PostMapping("/renameChannel")
    public String renameChannel(
            @RequestParam("channelId") int channelId,
            @RequestParam("channelName") String channelName
    ) {
        for (ChatChannel channel : channels.getAll()) {
            if (channel.getId() == channelId) {
                channel.setTitle(channelName);
            }
        }

        return "redirect:/channel";
    }

    @GetMapping("/renameChannel")
    public String renameChannelForm(
            @RequestParam("channelId") int channelId,
            @RequestParam("channelName") String channelName,
            Model model
    ) {
        model.addAttribute("channelId", channelId);
        model.addAttribute("channelName", channelName);

        return "RenameChannel";
    }
}
