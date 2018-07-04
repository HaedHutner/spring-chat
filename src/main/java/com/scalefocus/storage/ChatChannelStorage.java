package com.scalefocus.storage;

import com.scalefocus.model.ChatChannel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChatChannelStorage {

    public static final Integer DEFAULT_CHANNEL_ID = 0;
    public static final ChatChannel DEFAULT_CHANNEL = new ChatChannel("general");

    private static List<ChatChannel> channels;
    static {
        channels = new ArrayList<>(16);
        channels.add(DEFAULT_CHANNEL);
    }

    private ChatChannelStorage() {
    }

    public void register(ChatChannel channel) {
        channels.add(channel);
    }

    public Optional<ChatChannel> get(int id) {
        for ( ChatChannel channel : channels ) {
            if ( channel.getId() == id ) return Optional.of(channel);
        }
        return Optional.empty();
    }

    public List<ChatChannel> getAll() {
        return channels;
    }

    public void deleteChannel(int id) {
        for(ChatChannel channel : channels) {
            if(channel.getId() == id) {
                this.getAll().remove(channel);
            }
        }
    }

}
