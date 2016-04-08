package com.zooplus.forex;

import com.zooplus.forex.persistence.User;
import com.zooplus.forex.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            User predefinedUser = new User();
            predefinedUser.setLogin("tomslot");
            predefinedUser.setPassword("pwd");
            repository.save(predefinedUser);
            log.info("Created predefined user: " + predefinedUser);
        };
    }
}