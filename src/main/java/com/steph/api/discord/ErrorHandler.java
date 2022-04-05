package com.steph.api.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorHandler {

    public void sendErrorFeedback(String command, String message, Long userId, Event event) {
        event.getJDA().getUserById(userId).openPrivateChannel().complete().sendMessageFormat(message, userId, command).complete();
    }

    public void sendErrorFeedback(String command, String message, Long userId, MessageChannel messageChannel) {
        messageChannel.sendMessageFormat(message, userId, command).complete();
    }

}
