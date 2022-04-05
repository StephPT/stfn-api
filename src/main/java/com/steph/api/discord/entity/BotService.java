package com.steph.api.discord.entity;

import com.steph.api.discord.data.ServerInformation;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service("Bot")
public class BotService implements Bot {

    @Autowired
    private JDA jda;

    @Autowired
    @Qualifier(value = "serverList")
    private List<ServerInformation> servers;

    @Override
    public List<ServerInformation> getLocations() {
        return servers;
    }


}
