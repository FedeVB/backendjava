package com.backendjava.app;

import com.backendjava.app.models.entity.User;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Autowired
	private UserServiceInterface userService;
	@Override
	public void run(String... args) throws Exception {
//	userService.save(new User())
	}
}
