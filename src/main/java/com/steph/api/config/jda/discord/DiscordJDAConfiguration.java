package com.steph.api.config.jda.discord;

import com.steph.api.config.jda.listeners.ListenersConfiguration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
@ConditionalOnProperty(prefix = "discord.jda", name = "enabled", havingValue = "true")
@ConditionalOnBean(ListenersConfiguration.class)
@AutoConfigureAfter(ListenersConfiguration.class)
public class DiscordJDAConfiguration {

    @Autowired
    @Qualifier("discordConfiguration")
    private DiscordProperties properties;

    @Autowired
    private List<ListenerAdapter> adapters;

    @Bean
    public JDA jda() throws LoginException {
        return JDABuilder.create(properties.getToken(), GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES)
                .addEventListeners(adapters.toArray())
                .setStatus(properties.getStatus())
                .setActivity(Activity.competing(properties.getActivity()))
                .build();
    }

}
