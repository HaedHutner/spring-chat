package com.scalefocus.controller;

import com.scalefocus.model.ChatChannel;
import com.scalefocus.storage.ChatChannelStorage;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Controller
@RequestMapping("/channels")
public class ChannelController {

    private ChatChannelStorage channels;
    private UserStorage users;

    @Autowired
    private ChannelController(ChatChannelStorage channels, UserStorage users) {
        this.users = users;
        this.channels = channels;
    }

    @GetMapping
    public String redirect() {
        return "redirect:/channels/" + ChatChannelStorage.DEFAULT_CHANNEL_ID;
    }

    @GetMapping("/{channel_id}")
    public String channels(
            @CookieValue(value = UserController.USER_ID_COOKIE, required = false) Cookie userId,
            @PathVariable("channel_id") int channelId,
            Model model
    ) {
        if (userId == null || !users.getUser(userId.getValue()).isPresent()) {
            return "redirect:/users/create";
        }

        model.addAttribute("channels", channels.getAll());

        Optional<ChatChannel> currentChannel = channels.get(channelId);
        if (currentChannel.isPresent()) {
            model.addAttribute("currentChannel", currentChannel.get());
        } else {
            model.addAttribute("currentChannel", ChatChannelStorage.DEFAULT_CHANNEL);
        }

        return "Home";
    }

    @GetMapping("/create")
    public String createChannelForm() {
        return "CreateChannel";
    }

    @PostMapping("/create")
    public String createChannel(
            @RequestParam("channelName") String channelName
    ) {
        channels.register(new ChatChannel(channelName));
        return "redirect:/channels/" + ChatChannelStorage.DEFAULT_CHANNEL_ID;
    }

    @GetMapping("/{channel_id}/edit")
    public String editChannelForm(
            @PathVariable("channel_id") int channelId,
            Model model
    ) {
        channels.get(channelId).ifPresent(chatChannel -> model.addAttribute("channel", chatChannel));
        return "EditChannel";
    }

    @PostMapping("/{channel_id}/edit")
    public String editChannel(
            @PathVariable("channel_id") int channelId,
            @RequestParam("channelName") String channelName
    ) {
        channels.get(channelId).ifPresent(chatChannel -> chatChannel.setTitle(channelName));
        return "redirect:/channels/" + channelId;
    }

    @DeleteMapping("/{channel_id}/delete")
    public String deleteChannel(
            @PathVariable("channel_id") int channelId
    ) {
        channels.deleteChannel(channelId);
        return "redirect:/channels/" + channelId;
    }

    @GetMapping("/{channel_id}/delete")
    public String deleteChannelGet() {
        return "redirect:/channels/" + ChatChannelStorage.DEFAULT_CHANNEL_ID;
    }

}
