package com.example.SpringBootDemo.controllers;

import com.example.SpringBootDemo.models.User;
import com.example.SpringBootDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserRepository userRepository;

	//endpoint which returns all the users from the database but is accessible for logged-in users only
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<User> userAccess() {
		return userRepository.findAll();
	}
}
