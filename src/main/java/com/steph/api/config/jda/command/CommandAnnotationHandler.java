package com.steph.api.config.jda.command;

import com.steph.api.config.jda.discord.DiscordJDAConfiguration;
import com.steph.api.discord.commands.CommandRepository;
import net.dv8tion.jda.api.JDA;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
@AutoConfigureAfter(DiscordJDAConfiguration.class)
public class CommandAnnotationHandler {

    @Autowired
    private CommandConfiguration commandConfiguration;

    @Autowired
    private JDA jda;

    @Bean
    public Map<String, CommandRepository> commands() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<String, CommandRepository> result = new HashMap<>();
        Reflections reflections = new Reflections(commandConfiguration.getPath());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Command.class);
        for (Class<?> clazz : classes) {
            CommandRepository init = (CommandRepository) clazz.getDeclaredConstructors()[0].newInstance();
            init.setJda(jda);
            result.put(clazz.getAnnotation(Command.class).value(), init);
        }
        return result;
    }

}
