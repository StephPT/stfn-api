package com.steph.api.endpoints.discord.controllers.command;

import java.util.Arrays;
import java.util.List;

public class Command {

    private String command;

    private String[] args;

    public Command(String raw) {
        this.command = raw.split(" ")[0];
        this.args = raw.substring(raw.indexOf(' ')).split(" ");
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
