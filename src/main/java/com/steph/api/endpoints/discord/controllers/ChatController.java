package com.steph.api.endpoints.discord.controllers;

import com.steph.api.discord.commands.SecretSanta;
import com.steph.api.endpoints.discord.controllers.command.CommandService;
import com.steph.api.endpoints.discord.controllers.command.CommandUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ChatController extends ListenerAdapter {

    @Autowired
    private CommandService commandService;

    private static long STEPH_ID = 133963110078873600L;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
//        if(!CommandUtil.isCommand(event)) {
//            commandService.onCommand(new Command(event.getMessage().getContentRaw()));
//        }
        if(CommandUtil.isCommand(event) && CommandUtil.isExpectedUser(event.getAuthor(), STEPH_ID)) {
            List<User> members = event.getGuild().getMembers().stream().map(Member::getUser).filter(Predicate.not(User::isBot)).collect(Collectors.toList());
            SecretSanta santa = new SecretSanta();
            santa.setTemp(members);
            santa.execute();
        }
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        super.onSlashCommand(event);
    }
}
