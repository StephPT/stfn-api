package com.steph.api.config.jda.listeners;

import com.steph.api.config.jda.discord.DiscordJDAConfiguration;
import com.steph.api.discord.data.ServerInformation;
import com.steph.api.services.discord.command.CommandService;
import com.steph.api.services.discord.command.CommandUtil;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AutoConfigureBefore({DiscordJDAConfiguration.class, CommandService.class})
@ConditionalOnClass(JDABuilder.class)
public class ListenersConfiguration {

//    @Configuration
//    @AutoConfigureBefore(DiscordJDAConfiguration.class)
//    public static class Listeners {
//
//        private final List<ListenerAdapter> listeners = new ArrayList<>();
//
//        @Bean
//        public List<ListenerAdapter> adapters() {
//            return listeners;
//        }
//
//    }

    @Configuration
//    @AutoConfigureAfter(Listeners.class)
    public static class ServerListener extends ListenerAdapter {
        private List<ServerInformation> servers = new ArrayList<>();

        @Autowired
        private List<ListenerAdapter> adapters;

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

        @Bean(name = "serverList")
        public List<ServerInformation> getServers() {
            return servers;
        }
    }

    @Configuration
//    @AutoConfigureAfter(Listeners.class)
    public static class ChatListener extends ListenerAdapter {

        @Autowired
        private CommandService commandService;

        @Autowired
        private CommandUtil commandUtil;

        @Override
        public void onMessageReceived(@NotNull MessageReceivedEvent event) {
            if(commandUtil.isCommand(event)) {
                commandService.handle(event);
            }
        }

    }

}
