package com.scalefocus.model;

import java.util.ArrayList;

public class ChatChannel {

    private static ChatChannel instance;

    private ArrayList<ChatMessage> chatMessage;
    private String title;

    private ChatChannel() {
    }

    public ArrayList<ChatMessage> getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ArrayList<ChatMessage> chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ChatChannel getInstance() {
        if(instance == null) instance = new ChatChannel();
        return instance;
    }

    public void addMessage(ChatMessage newChatMessage) {
        this.chatMessage.add(newChatMessage);
    }

    public void deleteMessage(ChatMessage deleteChatMessage) {
        this.chatMessage.remove(deleteChatMessage);
    }
}
