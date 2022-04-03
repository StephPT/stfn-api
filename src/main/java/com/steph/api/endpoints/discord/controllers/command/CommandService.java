package com.steph.api.endpoints.discord.controllers.command;

import com.steph.api.config.jda.CommandConfiguration;
import com.steph.api.config.jda.Types;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    @Autowired
    private JDA jda;

    @Autowired
    private CommandConfiguration commandConfiguration;

    public void onCommand(Command command) {
        Types type = findType(command);
        if (type != null) {

        }
    }

    public Types findType(Command command) {
        Types type = null;
        if (commandConfiguration.getFun().containsKey(command.getCommand())) {
            type = Types.FUN;
        } else if (commandConfiguration.getAdmin().containsKey(command.getCommand())) {
            type = Types.ADMIN;
        } else if (commandConfiguration.getOther().containsKey(command.getCommand())) {
            type = Types.OTHER;
        }
        return type;
    }

}
