package com.scalefocus.model;

public class User {

    private String nickname;
    private String sessionId;

    public User(String nickname, String sessionId) {
        this.nickname = nickname;
        this.sessionId = sessionId;
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
