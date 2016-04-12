package com.zooplus.forex;

import com.zooplus.forex.model.CurrencyEnum;
import com.zooplus.forex.model.CurrencyQuery;
import com.zooplus.forex.model.ForexUser;
import com.zooplus.forex.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Date;

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


            CurrencyQuery sampleQuery = new CurrencyQuery();
            sampleQuery.setSrcCurrency(CurrencyEnum.AUD);
            sampleQuery.setDstCurrency(CurrencyEnum.EUR);
            sampleQuery.setAmount(100.);
            sampleQuery.setConvertedAmount(200.);
            sampleQuery.setTimestamp(new Date());
            sampleQuery.setDate(new Date());
            sampleQuery.setUser(predefinedUser);
            predefinedUser.setQueries(Collections.singletonList(sampleQuery));

            predefinedUser = repository.save(predefinedUser);

            log.info("Created predefined user: " + predefinedUser);
        };
    }
}