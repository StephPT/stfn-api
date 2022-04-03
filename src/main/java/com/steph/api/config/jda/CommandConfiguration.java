package com.steph.api.config.jda;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "command")
public class CommandConfiguration {

    private HashMap<String, Integer> admin;

    private HashMap<String, Integer> fun;

    private HashMap<String, Integer> other;

    public void setAdmin(HashMap<String, Integer> admin) {
        this.admin = admin;
    }

    public HashMap<String, Integer> getAdmin() {
        return admin;
    }

    public void setFun(HashMap<String, Integer> fun) {
        this.fun = fun;
    }

    public HashMap<String, Integer> getFun() {
        return fun;
    }

    public void setOther(HashMap<String, Integer> other) {
        this.other = other;
    }

    public HashMap<String, Integer> getOther() {
        return other;
    }
}
