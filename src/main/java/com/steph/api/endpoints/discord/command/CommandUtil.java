package com.steph.api.endpoints.discord.command;

import com.steph.api.config.jda.command.CommandType;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

@Service
public class CommandUtil {

    public boolean isCommand(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw().charAt(0) == '!' && !isUser(event.getAuthor());
    }

    public boolean isUser(User user) {
        return user.isBot();
    }

    public boolean isAuthorised(Member member, CommandType type, int permissions) {
        boolean result = false;
        if(CommandType.ADMIN.equals(type) || permissions == 2) {
            if(member.getPermissions().contains(Permission.ADMINISTRATOR)) {
                result = true;
            }
        } else {
            result = true;
        }
        return result;
    }

    public boolean isExpectedUser(User user, long userId) {
        return user.getIdLong() == userId;
    }

    public String[] createCommand(MessageReceivedEvent event) {
        String raw = event.getMessage().getContentRaw();
        raw = raw.substring(1);
        return raw.split(" ");
    }

}
