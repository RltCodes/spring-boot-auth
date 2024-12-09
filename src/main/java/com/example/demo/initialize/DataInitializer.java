package com.example.demo.initialize;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

@Configuration
public class DataInitializer {
	@Bean
	CommandLineRunner initRoles(final RoleRepository roleRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) {
				if (roleRepository.findByName("ROLE_USER")==null) {
					roleRepository.save(new Role("USER"));
				}
				
				if (roleRepository.findByName("ROLE_ADMIN") == null) {
					roleRepository.save(new Role("ADMIN"));
				}
			}
		};
	}
}
