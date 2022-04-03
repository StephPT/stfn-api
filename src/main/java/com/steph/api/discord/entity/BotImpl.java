package com.steph.api.discord.entity;

import com.steph.api.endpoints.discord.controllers.InitialisationController;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("Bot")
public class BotImpl implements Bot {

    @Autowired
    private JDA jda;

    @Autowired
    private InitialisationController controller;

    @RequestMapping(value = "/bot", method = RequestMethod.GET)
    @ResponseBody
    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        controller.getServers().forEach(serverInformation -> {
            stringBuilder.append(serverInformation.getServerName() + "\r");
        });
        return stringBuilder.toString();
    }


}
