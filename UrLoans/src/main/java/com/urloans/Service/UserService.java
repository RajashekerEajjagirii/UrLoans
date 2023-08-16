package com.urloans.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.urloans.Model.HomeLoans;
import com.urloans.Model.User;


public interface UserService {

	public User saveUser(User user);
	
	public List<User> getAllUsers();
	
	//HomeLoans Service
	
	public HomeLoans saveHomeLoans(HomeLoans homeLoans);
}
