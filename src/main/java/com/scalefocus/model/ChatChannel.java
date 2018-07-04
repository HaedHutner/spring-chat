package com.scalefocus.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class ChatChannel {

    private static ArrayList<ChatMessage> chatMessages = new ArrayList<>();
    private static String title;

    private ChatChannel() {
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
