package com.zooplus.forex;

import com.zooplus.forex.model.ForexUser;
import com.zooplus.forex.model.UserRepository;
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
    public CommandLineRunner createPredefinedUser(UserRepository repository) {
        return (args) -> {
            ForexUser predefinedUser = new ForexUser();
            predefinedUser.setLogin("user");
            predefinedUser.setPassword("user");
            repository.save(predefinedUser);
            log.info("Created predefined user: " + predefinedUser);
        };
    }
}