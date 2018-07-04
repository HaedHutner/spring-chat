package com.scalefocus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ChatMessage {

    private User sender;
    private String message;
    private long messageId;
    private static long count = 0;

    private LocalDateTime dateTime = LocalDateTime.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public ChatMessage(User sender, String message) {
        this.sender = sender;
        this.message = message;
        this.messageId = count++;
    }

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeStampAsString() {
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }
}
