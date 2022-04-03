package com.steph.api.discord.data;

public class ServerInformation {

    private String serverName;

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getCount() {
        return count;
    }

    public String getServerName() {
        return serverName;
    }
}
