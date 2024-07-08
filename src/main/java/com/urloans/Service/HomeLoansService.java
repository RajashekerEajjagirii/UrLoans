package com.urloans.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.urloans.exception.UrLoansNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.urloans.Model.HomeLoans;
import com.urloans.Repository.HomeLoansRepo;

@Service
public class HomeLoansService {
	
	@Autowired
	private HomeLoansRepo homeLoansRepo;
	
	//adding HomeLoans Data
	public HomeLoans saveHomeLoans(HomeLoans homeLoans) {
		HomeLoans hl=null;
		hl= homeLoansRepo.save(homeLoans);
		return hl;
	}

	
	//Getting All PersonalLoan USers Data
	public List<HomeLoans> getAll() {
		List<HomeLoans> hl=null;
		
		hl= homeLoansRepo.findAll(Sort.by(Order.desc("createdAt")));
		return hl;
	}


	//Get userdata by id
	
	public Optional<HomeLoans> getUserByid(Integer id) {
		Optional<HomeLoans> hl=homeLoansRepo.findById(id);
		if(hl.isPresent()) {
			return Optional.of(hl.get());
		}
//		throw new RuntimeException("User is not found "+id);
		throw new UrLoansNotFoundException("User not found with id "+id);
	}


	//Updating userData by id
	
	public HomeLoans updateUser(int id, HomeLoans homeloans) {
			HomeLoans newuser=null;
			Optional<HomeLoans> hl=homeLoansRepo.findById(id);
			if(hl.isPresent()) {
				newuser=hl.get();
				newuser.setFullName(homeloans.getFullName());
				newuser.setEmail(homeloans.getEmail());
				newuser.setMobileNum(homeloans.getMobileNum());
				newuser.setCity(homeloans.getCity());
				newuser.setOccupationType(homeloans.getOccupationType());
				newuser.setLoanAmount(homeloans.getLoanAmount());
				newuser.setMonthlySalary(homeloans.getMonthlySalary());
				newuser.setMonthlyEmi(homeloans.getMonthlyEmi());
				newuser.setTenure(homeloans.getTenure());
				newuser.setDob(homeloans.getDob());
				newuser.setAddress(homeloans.getAddress());
			 return	homeLoansRepo.save(newuser);
			}
		throw new RuntimeException("User is not found");
	}


//	Delete User by id
//	public HomeLoans deleteUser(int id) {
//		Optional<HomeLoans> hl=homeLoansRepo.findById(id);
//		if(hl.isPresent()) {
//			 homeLoansRepo.deleteById(id);
//		}
//		throw new RuntimeException("User not found,Delete operation failed");
//		
//
//	}

}
