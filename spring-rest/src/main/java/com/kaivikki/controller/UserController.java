package com.kaivikki.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.kaivikki.exception.UserNotFoundException;
import com.kaivikki.model.User;
import com.kaivikki.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
public class UserController {
	

	@Autowired
	private UserRepository userRepository;

	// Get all users, response status code = 200 if success and 500 if error
	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Get user by id,response status = 200 if found,404 if not found.
	// If Found : Send user details with response status code as 200
	// If Not Found: Throw UserNotFoundException which is tagged with response
	// status code of 404 and handled by CustomResponseEntityExceptioHandler
	// which send the error back is specified format.
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> getUserById(@PathVariable Integer id) {
		 Optional<User> findById = userRepository.findById(id);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("No User Found With Id " + id);
		}
		User user = findById.get();
		EntityModel<User> userEntityModel = EntityModel.of(user);
		WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		userEntityModel.add(webMvcLinkBuilder.withRel("all-users"));
		return userEntityModel;
	}

	// Create new user, response status = 201 created and 500 if error
	@PostMapping(path = "/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
}
