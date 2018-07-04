package com.scalefocus.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

    public ChatMessage getMessageToDelete(long messageId) {
        for (ChatMessage messageToDelete : chatMessages) {
            if (messageToDelete.getMessageId() == messageId) {
                return messageToDelete;
            }
        }
        return null;
    }

}
