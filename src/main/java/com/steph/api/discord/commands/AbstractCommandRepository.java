package com.steph.api.discord.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCommandRepository implements CommandRepository {

    public static final int ALL = 0;

    public static final int RESTRICTED = 1;

    public static final int ADMIN = 2;

    protected Object object;

    protected JDA jda;

    protected String[] args;

    protected RestAction<?> action;

    protected RestAction<?> queue;

    protected int delay = 10;

    @Override
    public boolean execute() {
        queue();
        if(action != null) {
            action.complete();
            action = null;
        } else if(queue != null) {
            queue.queue(message -> ((Message) message).delete().queueAfter(delay, TimeUnit.SECONDS));
            queue = null;
            delay = 10;
        }
        return true;
    }

    @Override
    public void setJda(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public void setObject(Object object) {
        this.object = object;
    }
}
