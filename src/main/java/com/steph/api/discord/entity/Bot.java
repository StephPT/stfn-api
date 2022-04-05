package com.steph.api.discord.entity;

import com.steph.api.discord.data.ServerInformation;

import java.util.List;

public interface Bot {
    List<ServerInformation> getLocations();
}
