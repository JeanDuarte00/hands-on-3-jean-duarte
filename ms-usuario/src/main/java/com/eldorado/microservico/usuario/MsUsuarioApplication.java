package com.eldorado.microservico.usuario;

import com.eldorado.commons.interception.EnableAuthorization;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableRabbit
@EnableAuthorization
@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class MsUsuarioApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsUsuarioApplication.class, args);
    }

}
