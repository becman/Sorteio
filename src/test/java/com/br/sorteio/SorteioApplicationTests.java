package com.br.sorteio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class SorteioApplicationTests {

	public static void main(String[] args) {
		Random random = new Random();

		System.out.println("Número inteiro aleatório de 0 até 10: " + random.nextInt(10));
	}
	@Test
	void contextLoads() {
	}

}
