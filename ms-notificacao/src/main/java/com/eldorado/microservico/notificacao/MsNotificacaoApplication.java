package com.eldorado.microservico.notificacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsNotificacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificacaoApplication.class, args);
	}

}
