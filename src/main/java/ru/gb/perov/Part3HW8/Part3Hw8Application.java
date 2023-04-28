package ru.gb.perov.Part3HW8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ru.gb.perov.Part3HW8.Data")
public class Part3Hw8Application {

    public static void main(String[] args) {
        SpringApplication.run(Part3Hw8Application.class, args);
//        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
//        SessionFactoryUtils.init();
    }
}
