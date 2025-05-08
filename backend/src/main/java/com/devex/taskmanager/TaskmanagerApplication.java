package com.devex.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaskmanagerApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("ENCODE = " + passwordEncoder.encode("minhasenha"));
		boolean result = passwordEncoder.matches("123459", "$2a$10$25/CV8b3pE27f8knmSu2J.hIZBA6a5Sy2Q1CdAjq18MWyzRau9x7a");

		System.out.println("RESULTADO = " + result);
	}
}
