package com.steph.api.discord.commands;

import com.steph.api.config.jda.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

@Command("hl")
public class HigherLower extends AbstractCommandRepository {

    @Override
    public void queue() throws NumberFormatException {
        int high = new Random().nextInt(10);
        int guess = Integer.parseInt(args[1]);
        String message;
        if(guess == high) {
            message = "<@%s> you were right!";
        } else if(guess < high) {
            message = "<@%s> you were lower!";
        } else {
            message = "<@%s> you were higher!";
        }
        message += " it was %s";
        MessageReceivedEvent out = (MessageReceivedEvent) object;
        action = out.getChannel().sendMessageFormat(message, out.getAuthor().getIdLong(), high);
    }

}
