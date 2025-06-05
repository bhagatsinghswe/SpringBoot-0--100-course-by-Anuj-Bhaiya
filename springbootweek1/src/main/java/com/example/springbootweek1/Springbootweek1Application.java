package com.example.springbootweek1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Springbootweek1Application implements CommandLineRunner {

	@Autowired
	Apple obj;

	@Autowired
	DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(Springbootweek1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		obj.eatApple();

		System.out.println(dbService.getData());
	}
}
