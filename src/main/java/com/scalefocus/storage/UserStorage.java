package com.scalefocus.storage;

import com.scalefocus.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserStorage {

    private static Map<String, User> users = new HashMap<>();

    private UserStorage() {
    }

    public Optional<User> getUser(String sessionId) {
        return Optional.ofNullable(users.get(sessionId));
    }

    public void addUser(User user) {
        try {
            users.put(user.getSessionId(), user);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public Collection<User> getAll() {
        return users.values();
    }

}
