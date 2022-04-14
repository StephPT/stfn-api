package com.steph.api.discord.commands.admin;

import com.steph.api.config.jda.command.Command;
import com.steph.api.discord.commands.AbstractCommandRepository;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@Command("purge")
public class Purge extends AbstractCommandRepository {

    @Override
    public void queue() {
        MessageReceivedEvent event = (MessageReceivedEvent) object;

        List<Message> messages = event.getChannel().getHistory().retrievePast(Math.min(Integer.parseInt(args[1]), 100)).complete();
        messages.forEach(message -> event.getChannel().deleteMessageById(message.getId()).queue());
        delay = 2;
        queue = event.getChannel().sendMessageFormat("Deleting Message...");
    }
}
