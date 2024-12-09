package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error, Model model) {
		System.out.println(error); 	
		if (error != null) {
			model.addAttribute("error", "Invalid username or password.");
		}
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	 
	@PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam(required = true) ArrayList<String> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // Convertir les noms des rôles en objets Role
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String roleName : roles) {
                List<Role> rolesObj = roleRepository.findByName(roleName);
                if (roles != null) {
                    roleSet.add(rolesObj.get(0));
                }
            }
        }
        user.setRoles(roleSet);

        // Enregistrer l'utilisateur
        userRepository.save(user);
        System.out.println(user.toString());

        return "redirect:/login";
    }

	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
}
