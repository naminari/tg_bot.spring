package com.java.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.bot.repository.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class AppConfig {

    @Value("${app.db.bot-db.driver}")
    private String driver;

    @Value("${app.db.bot-db.url}")
    private String url;

    @Value("${app.db.bot-db.user}")
    private String user;

    @Value("${app.db.bot-db.password}")
    private String password;

    @Bean
    public Bot myBot() {
        return new Bot();
    }

    @Bean
    ObjectMapper customObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }
}
