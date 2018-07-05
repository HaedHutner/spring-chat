package com.scalefocus.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ChatMessage {

    private long id;

    private User sender;
    private String message;

    private static long count = 0;

    private LocalDateTime dateTime = LocalDateTime.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public ChatMessage(User sender, String message) {
        this.sender = sender;
        this.message = message;
        this.id = count++;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
