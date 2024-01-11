package com.br.sorteio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SorteioApplication {

	public static void main(String[] args) {
		//System.out.println(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(SorteioApplication.class, args);
	}

}
