package com.steph.api.config.jda;

import com.steph.api.endpoints.discord.controllers.ChatController;
import com.steph.api.endpoints.discord.controllers.InitialisationController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;

@Configuration
public class DiscordJDAConfiguration {

    @Value("${discord.jda.token}")
    private String token;

    @Value("${discord.jda.activity}")
    private String activity;

    @Autowired
    private List<ListenerAdapter> adapters;

    @Bean
    public JDA jda() throws LoginException {
        return JDABuilder.create(token, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES)
                .addEventListeners(new InitialisationController(), new ChatController())
                .setStatus(OnlineStatus.OFFLINE)
                .setActivity(Activity.competing(activity))
                .build();
    }

}
