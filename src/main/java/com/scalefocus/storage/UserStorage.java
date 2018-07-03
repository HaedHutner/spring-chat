package com.scalefocus.storage;

import com.scalefocus.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserStorage {

    private static UserStorage instance;

    private Map<String, User> users = new HashMap<>();

    private UserStorage() {
    }

    public Optional<User> getUser(String sessionId) {
        return Optional.ofNullable(users.get(sessionId));
    }

    public void addUser(User user) {
        this.users.put(user.getSessionId(), user);
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public static UserStorage getInstance() {
        if (instance == null) instance = new UserStorage();
        return instance;
    }

}
