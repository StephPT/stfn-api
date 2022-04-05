package com.steph.api.config.jda.command;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "discord")
public class CommandConfiguration {

    private String path;

    private HashMap<String, CommandInformation> command;

    public void setCommand(HashMap<String, CommandInformation> command) {
        this.command = command;
    }

    public HashMap<String, CommandInformation> getCommand() {
        return command;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static class CommandInformation {

        private String execution;

        private int args;

        private int permissionLevel;

        private CommandType type;

        public void setExecution(String execution) {
            this.execution = execution;
        }

        public String getExecution() {
            return execution;
        }

        public void setArgs(int args) {
            this.args = args;
        }

        public int getArgs() {
            return args;
        }

        public void setPermissionLevel(int permissionLevel) {
            this.permissionLevel = permissionLevel;
        }

        public int getPermissionLevel() {
            return permissionLevel;
        }

        public void setType(CommandType type) {
            this.type = type;
        }

        public CommandType getType() {
            return type;
        }
    }
}
