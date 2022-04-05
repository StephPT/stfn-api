package com.steph.api;

import com.steph.api.config.jda.command.CommandConfiguration;
import com.steph.api.config.jda.discord.DiscordProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CommandConfiguration.class, DiscordProperties.class})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
