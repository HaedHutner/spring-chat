package com.scalefocus.model;

import java.util.ArrayList;
import java.util.Optional;

public class ChatChannel {

    private static int count = 0;

    private int id;

    private String title;

    private ArrayList<ChatMessage> chatMessages = new ArrayList<>();

    public ChatChannel(String title) {
        this.title = title;
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public void addMessage(ChatMessage newChatMessage) {
        this.chatMessages.add(newChatMessage);
    }

    public void deleteMessage(ChatMessage deleteChatMessage) { this.chatMessages.remove(deleteChatMessage); }

    public Optional<ChatMessage> getMessageById(long messageId) {
        for (ChatMessage msg : chatMessages) {
            if (msg.getMessageId() == messageId) {
                return Optional.of(msg);
            }
        }
        return Optional.empty();
    }
}
