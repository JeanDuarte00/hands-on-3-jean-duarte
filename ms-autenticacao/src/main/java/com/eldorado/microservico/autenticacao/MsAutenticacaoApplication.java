package com.eldorado.microservico.autenticacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAutenticacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAutenticacaoApplication.class, args);
	}

}
