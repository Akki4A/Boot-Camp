package com.bootcamp.hrm.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.bootcamp.hrm")
@EntityScan("com.bootcamp.hrm")
@EnableJpaRepositories("com.bootcamp.hrm")
@SpringBootApplication
public class HrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmApplication.class, args);
    }

}
