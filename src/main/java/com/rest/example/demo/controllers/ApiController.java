package com.rest.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rest.example.demo.models.User;
import com.rest.example.demo.repo.UserRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/*
controller to handle requests from browser client (GUI client)
*/
@Controller
@Tag(name = "User Operations using web UI", description = "Endpoints for user operationsusing web UI")
public class ApiController {

    @Autowired
    private UserRepo userRepo;

    //request for index(home) page
    @Operation(summary = "Get home page", description = "dsiplays home(index) page")  //this annotation is used for API documentation 
    @GetMapping(value="/index")
	public String getWelcomePage() {
		return "index";
	}
    
    //request for list users page
    @Operation(summary = "user list page", description = "dsiplays list of all users") //this annotation is used for API documentation 
    @GetMapping("/list")
    public String getAllUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }
    
    //request for list users page using Post method
    @Operation(summary = "user list page", description = "dsiplays list of all users") //this annotation is used for API documentation  
    @PostMapping("/list")
    public String getAllUsersUsingPostMethod(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    //request for add user page (form) to come 
    @Operation(summary = "add user form", description = "dsiplays a form to add a user") //this annotation is used for API documentation 
    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    //request sent from add user page to add user to DB after filling the forms
    @Operation(summary = "add user", description = "adds a user to DB") //this annotation is used for API documentation 
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userRepo.save(user);
        return "forward:/list"; //better to use forward instead of redirect for network performance
    }

    @Operation(summary = "edit user page", description = "dsiplays a form to add a user") //this annotation is used for API documentation     
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "edit-user";
    }

    @Operation(summary = "edit user", description = "saves eddied user detail to DB") //this annotation is used for API documentation 
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/list";
    }

    @Operation(summary = "delete user", description = "deletes a user permanently") //this annotation is used for API documentation 
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/list";
    }
}
