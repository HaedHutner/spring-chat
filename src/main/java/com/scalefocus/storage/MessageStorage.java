package com.scalefocus.storage;

import com.scalefocus.model.ChatMessage;
import com.scalefocus.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class MessageStorage {

    private ArrayList<ChatMessage> messageStorage = new ArrayList<>();

    public MessageStorage() {
    }

    @GetMapping("/channel")
    public ChatMessage[] getMessagesByUser(@PathVariable User wantedUser){
        ChatMessage[] messagesByUser = new ChatMessage[];
        for(ChatMessage currentMessage : messageStorage)
            if(currentMessage.getSender() == wantedUser)



    }
}
