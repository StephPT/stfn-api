package com.steph.api.endpoints.discord.command;

import com.steph.api.config.jda.command.CommandConfiguration;
import com.steph.api.discord.ErrorHandler;
import com.steph.api.discord.commands.CommandRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommandService {

    @Autowired
    private CommandConfiguration commandConfiguration;

    @Autowired
    private CommandUtil commandUtil;

    @Autowired
    private Map<String, CommandRepository> commandsMap;

    @Autowired
    private ErrorHandler errorHandler;

    public void handle(MessageReceivedEvent event) {
        String[] command = commandUtil.createCommand(event);
        try {
            if(commandsMap.containsKey(command[0])) {
                CommandRepository cmd = commandsMap.get(command[0]);
                CommandConfiguration.CommandInformation info = commandConfiguration.getCommand().get(cmd.getClass().getSimpleName());
                if((command.length) == (info.getArgs() + 1)) {
                    if(commandUtil.isAuthorised(event.getMember(), info.getType(), info.getPermissionLevel())) {
                        cmd.setArgs(command);
                        cmd.setObject(event);
                        cmd.execute();
                    }
                } else {
                    throw new IllegalAccessException("Missing args");
                }
            } else {
                throw new NullPointerException("Command Doesn't Exist");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            errorHandler.sendErrorFeedback(command[0], "Sorry <@%s>! The command '%s' doesn't exist! ☹️", event.getAuthor().getIdLong(), event.getChannel());
        } catch (Exception e) {
            e.printStackTrace();
            errorHandler.sendErrorFeedback(command[0], "Sorry <@%s>! It looks like something went wrong with %s ☹️", event.getAuthor().getIdLong(), event.getChannel());
        }
    }

}
