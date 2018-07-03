package com.scalefocus.model;

import java.util.UUID;

public class User {

    private String nickname;
    private String sessionId;

    {
        this.sessionId = UUID.randomUUID().toString();
    }

    public User(String nickname) {
        this.nickname = nickname;
    }

    public User() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
