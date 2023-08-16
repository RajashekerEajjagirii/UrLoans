package com.urloans.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.urloans.Model.HomeLoans;
import com.urloans.Model.User;
import com.urloans.Repository.HomeLoansRepo;
import com.urloans.Repository.UserRepository;

@Service
public class UserServiceImpln implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	@Override
	public List<User> getAllUsers(){
//		return userRepo.findAll();
		return userRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	//HomeLoans
	@Autowired
	private HomeLoansRepo homeLoansRepo;
	
	public HomeLoans saveHomeLoans(HomeLoans homeLoans) {
		return homeLoansRepo.save(homeLoans);
	}
	
}
