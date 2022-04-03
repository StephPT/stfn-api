package com.steph.api.endpoints.discord.controllers.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandUtil {

    public static boolean isCommand(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw().charAt(0) == '!' && !isUser(event.getAuthor());
    }

    public static boolean isUser(User user) {
        return user.isBot();
    }

    public static boolean isAuthorised(Member member) {
        return member.getRoles().stream().anyMatch(role -> role.getPermissions().stream().anyMatch(permission -> permission == Permission.ADMINISTRATOR));
    }

    public static boolean isExpectedUser(User user, long userId) {
        return user.getIdLong() == userId;
    }

}
