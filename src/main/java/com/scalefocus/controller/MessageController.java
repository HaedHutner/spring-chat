package com.scalefocus.controller;

import com.scalefocus.model.ChatMessage;
import com.scalefocus.storage.ChatChannelStorage;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/channels/{channel_id}/messages")
public class MessageController {

    private ChatChannelStorage channels;
    private UserStorage users;

    @Autowired
    private MessageController(ChatChannelStorage channels, UserStorage users) {
        this.users = users;
        this.channels = channels;
    }

    @PostMapping("post")
    public String postMessage(
            @PathVariable("channel_id") int channelId,
            @RequestParam("msg") String message,
            @CookieValue(UserController.USER_ID_COOKIE) String userId
    ) {
        channels.get(channelId).ifPresent(chatChannel -> {
            users.getUser(userId).ifPresent(user -> {
                chatChannel.addMessage(new ChatMessage(user, message));
            });
        });

        return "redirect:/channels/" + channelId;
    }

    @GetMapping("/{message_id}/edit")
    public String editMessageForm(
            @PathVariable("channel_id") int channelId,
            @PathVariable("message_id") long messageId,
            Model model
    ) {
        channels.get(channelId).ifPresent(chatChannel -> {
            model.addAttribute("channel", chatChannel);
            chatChannel.getMessageById(messageId).ifPresent(message -> {
                model.addAttribute("message", message);
            });
        });

        return "EditMessage";
    }

    @PostMapping("/{message_id}/edit")
    public String editMessage(
            @PathVariable("channel_id") int channelId,
            @PathVariable("message_id") long messageId,
            @RequestParam("msg") String newMessage
    ) {
        channels.get(channelId).ifPresent(chatChannel -> {
            chatChannel.getMessageById(messageId).ifPresent(message -> message.setMessage(newMessage));
        });

        return "redirect:/channels/" + channelId;
    }

    @DeleteMapping("/{message_id}/delete")
    public String deleteMessage(
            @PathVariable("channel_id") int channelId,
            @PathVariable("message_id") long messageId
    ) {
        channels.get(channelId).ifPresent(chatChannel -> {
            chatChannel.getMessageById(messageId).ifPresent(chatChannel::deleteMessage);
        });

        return "redirect:/channels/" + channelId;
    }

    @GetMapping("/{message_id}/delete")
    public String deleteMessage(
            @PathVariable("channel_id") int channelId
    ) {
        return "redirect:/channels/" + channelId;
    }

}
