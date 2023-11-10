package com.java.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.bot.repository.Bot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Bot myBot(){
        return new Bot();
    }

    @Bean
    ObjectMapper customObjectMapper(){
        return new ObjectMapper();
    }
}
