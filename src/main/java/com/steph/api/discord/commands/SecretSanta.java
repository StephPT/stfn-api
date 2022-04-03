package com.steph.api.discord.commands;

import net.dv8tion.jda.api.entities.User;

import java.util.*;
import java.util.stream.Collectors;

public class SecretSanta extends AbstractCommandRepository {

    @Override
    public boolean execute() {
        List<User> users = (List<User>) getTemp();
        List<String> presents = sort(users).stream().map(User::getName).collect(Collectors.toList());
        Map<User, String> secret = new HashMap<>();
        for(int i = 0; i < users.size() - 1; i++) {
            secret.put(users.get(i), presents.get(i));
        }

        secret.forEach((k, v) -> {
            k.openPrivateChannel().complete().sendMessage("You've got " + v).queue();
        });

        return false;
    }

    public List<User> sort(final List<User> u) {
        List<User> users = new LinkedList<>();
        users.addAll(u);
        Random rng = new Random();
        for (int i = users.size()-1; i > 0; i--) {
            int j = rng.nextInt(i);
            Object tmp = users.get(i);
            users.set(i, users.get(j));
            users.set(j, (User) tmp);
        }
        return users;
    }
}
