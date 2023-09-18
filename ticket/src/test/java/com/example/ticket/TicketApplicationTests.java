package com.example.ticket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class TicketApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("UUID----------------------------------");
		System.out.println(UUID.randomUUID());
	}

}
