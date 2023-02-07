package com.backendjava.app;

import com.backendjava.app.models.entity.Publication;
import com.backendjava.app.models.entity.User;
import com.backendjava.app.models.repository.PublicationRepository;
import com.backendjava.app.models.repository.UserRepository;
import com.backendjava.app.services.interfaces.RoleService;
import com.backendjava.app.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
//		User user=userServiceInterface.getById(1);
//		user.getRoles().add(roleService.findByRoleName("USER"));
//		user.getRoles().add(roleService.findByRoleName("ADMIN"));
//		userRepository.save(user);
		List<Publication> publications=publicationRepository.findByUserId(1);
		for (Publication aux: publications) {
			System.out.println(aux.getTittle());
		}
	}
}
