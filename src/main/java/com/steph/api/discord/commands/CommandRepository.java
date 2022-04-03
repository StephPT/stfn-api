package com.steph.api.discord.commands;

public interface CommandRepository {

    boolean execute();

    void queue();
}
