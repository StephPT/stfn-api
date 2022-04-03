package com.steph.api.discord.commands;

public abstract class AbstractCommandRepository implements CommandRepository {

    private Object temp;

    public void setTemp(Object temp) {
        this.temp = temp;
    }

    protected Object getTemp() {
        return temp;
    }

    @Override
    public void queue() {};
}
