package com.scalefocus.controller;

import com.scalefocus.model.ChatChannel;
import com.scalefocus.storage.ChatChannelStorage;
import com.scalefocus.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/channels")
public class ChannelController {

    ChatChannelStorage channels;
    UserStorage users;

    @Autowired
    private ChannelController(ChatChannelStorage channels, UserStorage users) {
        this.users = users;
        this.channels = channels;
    }

    @GetMapping("/{channel_id}")
    public String channels(
            @PathVariable("channel_id") int channelId,
            Model model
    ) {

        model.addAttribute("channels", channels.getAll());

        channels.get(channelId).ifPresent(chatChannel -> model.addAttribute("currentChannel", chatChannel));

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
        return "RenameChannel";
    }

    @PutMapping("/{channel_id}/edit")
    public String editChannel(
            @PathVariable("channel_id") int channelId,
            @RequestParam("channelName") String channelName
    ) {
        channels.get(channelId).ifPresent(chatChannel -> chatChannel.setTitle(channelName));
        return "redirect:/channels/" + channelId;
    }

    @PostMapping("/{channel_id}/delete")
    public String deleteChannel(
            @PathVariable("channel_id") int channelId
    ) {
        channels.deleteChannel(channelId);
        return "redirect:/channels/" + channelId;
    }

}
