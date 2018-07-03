package com.scalefocus.storage;

import com.scalefocus.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserStorage {

    private Map<String,User> users = new HashMap<>();

    private UserStorage() {}

    public Optional<User> getUser(String sessionId) {
        return Optional.ofNullable(users.get(sessionId));
    }

    public void addUser(User user) {
        this.users.put(user.getSessionId(), user);
    }

}
