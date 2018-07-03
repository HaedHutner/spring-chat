package com.scalefocus.model;

public class ChatMessage {

    private User sender;
    private String message;
    private long messageId;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public ChatMessage(User sender, String message) {

        this.sender = sender;
        this.message = message;
    }
}
