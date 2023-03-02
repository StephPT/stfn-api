package com.steph.api.services.discord.bot;

import com.steph.api.discord.data.ServerInformation;
import com.steph.api.discord.entity.Bot;
import com.steph.api.services.controller.BasicRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class BotController implements BasicRestController<ServerInformation> {

    @Autowired
    private Bot bot;

    @Override
    public ServerInformation get(String uuid) {
        return null;
    }

    @Override
    public ServerInformation save(ServerInformation entity) {
        return null;
    }

    @Override
    public void delete(ServerInformation entity) {

    }

    @Override
    @RequestMapping(value = "/bot/locations", method = RequestMethod.GET)
    public List<ServerInformation> getAll() {
        return bot.getLocations();
    }
}
