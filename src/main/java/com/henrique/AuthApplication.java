package com.henrique;

import com.henrique.models.Client;
import com.henrique.models.Role;
import com.henrique.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Autowired
	ClientService clientService;
	@Override
	public void run(String... args) throws Exception {



		Client clien = new Client();
		clien.setEmail("hen@gmail.com");
		clien.setPassword("123456");
		clien.addProfile(Role.ADMIN);
		clientService.save(clien);


		Client clien2 = new Client();
		clien2.setEmail("hen2@gmail.com");
		clien2.setPassword("123456");
		clientService.save(clien2);

		Client clien3 = new Client();
		clien3.setEmail("hen3@gmail.com");
		clien3.setPassword("123456");
		clientService.save(clien3);
	}

}
