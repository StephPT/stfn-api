package com.steph.api.discord.commands.other;

import com.steph.api.config.jda.command.Command;
import com.steph.api.discord.commands.AbstractCommandRepository;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@Command("ping")
public class Ping extends AbstractCommandRepository {

    @Override
    public void queue() {
        Message msg = ((MessageReceivedEvent) object).getMessage();
        long time = System.currentTimeMillis();
        msg.getChannel().sendMessage("Pong!").queue(response ->
                response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time
        ).queue());
    }

}
