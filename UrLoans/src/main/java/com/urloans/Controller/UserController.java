package com.urloans.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.urloans.Model.HomeLoans;
import com.urloans.Model.User;
import com.urloans.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public String add(@RequestBody User user) {
		userService.saveUser(user);
		return "New User registered successfully";
	}
	
	@GetMapping("/getAll")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//adding HomeLoans Data
	

	@PostMapping("/homeloans/add")
	public String add(@RequestBody HomeLoans homeLoans) {
		userService.saveHomeLoans(homeLoans);
		return "New Home Loan request added successfully";
		
	}
}
