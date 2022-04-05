package com.steph.api.discord.commands;

import net.dv8tion.jda.api.JDA;

public interface CommandRepository {

    boolean execute();

    void queue();

    void setJda(JDA jda);

    void setArgs(String[] args);

    void setObject(Object object);
}
