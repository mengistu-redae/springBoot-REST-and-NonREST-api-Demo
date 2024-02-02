package com.rest.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.example.demo.models.User;
import com.rest.example.demo.repo.UserRepo;

import io.swagger.v3.oas.annotations.tags.Tag;

/*
	controller to handle requests from REST client (none GUI client like Postman that use rest features)
*/
@RestController
@Tag(name = "REST user operations", description = "REST Endpoints for user operations")
public class RestApiController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping(value="/")
	public String getPage() {
		String welcomeMessage = "<div style=\"margin: 40 auto; width: 50%;\">"
				+ "<Strong>Welcome! </strong><br>"
				+ "This is from REST API end point.<br><br>"
				+ "You can see all APIs (Both REST - postman and Non-REST - browser UI clients) "
				+ "in the swagger API documentation tool using the below URLs.<br> "
				+ "<ul>"
				+ "<li><a href=\"http://localhost:8080/swagger-ui/index.html\" target=\"_blank\">http://localhost:8080/swagger-ui/index.html</a></li>"
				+ "<li><a href=\"http://localhost:8080/v3/api-docs\" target=\"_blank\">http://localhost:8080/v3/api-docs</a></li>"
				+ "<li><a href=\"http://localhost:8080/v3/api-docs.yaml\" target=\"_blank\">http://localhost:8080/v3/api-docs.yaml</a></li>"
				+ "</ul>"
				+ "(assuming that the application is running on port 8080).<br><br>"
				+ "Swagger UI provides an interactive interface where you can explore the "
				+ "API endpoints, send requests, and view responses."
				+ "</div>";
		
		return welcomeMessage;
	}
	
	@GetMapping(value="/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	@PostMapping(value = "/save")
	public String saveUser(@RequestBody User user) {
		userRepo.save(user);
		
		return "user saved to database...";
	}
	
	@PutMapping(value = "update/{id}")
	public String updateUser(@PathVariable long id, @RequestBody User user) {
		
		User updatedUser = userRepo.findById(id).get();
		
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setAge(user.getAge());
		updatedUser.setOccupation(user.getOccupation());
		
		userRepo.save(updatedUser);
		
		return "user updated...";
	}
	
	@DeleteMapping(value="delete/{id}")
	public String deleteUser(@PathVariable long id) {
		
		User userToBeDeleted = userRepo.findById(id).get();
		userRepo.delete(userToBeDeleted); //or userRepo.deleteById(id);
		
		return "user with id - " + id + " - Deleted...";
	}
}























