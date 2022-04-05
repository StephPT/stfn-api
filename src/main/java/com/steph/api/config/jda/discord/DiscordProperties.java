package com.steph.api.config.jda.discord;

import net.dv8tion.jda.api.OnlineStatus;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "discordConfiguration")
@ConfigurationProperties(prefix = "discord.jda")
public class DiscordProperties {

    private String token;

    private String activity;

    private OnlineStatus status;

    private boolean enabled;

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
