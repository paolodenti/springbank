package com.github.paolodenti.springbank.user.query.api;

import com.github.paolodenti.springbank.user.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryApplication.class, args);
    }

}
