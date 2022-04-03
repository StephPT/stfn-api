package com.steph.api.endpoints.discord.controllers;

import com.steph.api.discord.data.ServerInformation;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitialisationController extends ListenerAdapter {

    private List<ServerInformation> servers = new ArrayList<>();

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        addServer(event.getGuild().getName());
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA().getGuildCache().forEach(e -> {
            addServer(e.getName());
        });
    }

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        super.onShutdown(event);
    }

    public void addServer(String server) {
        final ServerInformation information = new ServerInformation();
        information.setServerName(server);
        information.setCount(servers.size());
        servers.add(information);
    }

    public List<ServerInformation> getServers() {
        return servers;
    }
}
