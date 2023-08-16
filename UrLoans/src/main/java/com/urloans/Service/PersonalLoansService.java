package com.urloans.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;


import com.urloans.Model.PersonalLoans;
import com.urloans.Repository.PersonalLoansRepo;

@Service
public class PersonalLoansService {
	
	@Autowired
	private PersonalLoansRepo personalLoansRepo;
	
	
	//Adding Personal Loans Data
	
	public PersonalLoans savePersonalLoans(PersonalLoans personalLoans) {
		PersonalLoans pl=null;
		
		pl= personalLoansRepo.save(personalLoans);
	 
		return pl;
	}

	
	// Getting All Users Data
	public List<PersonalLoans> getAllUsers() {
		List<PersonalLoans> perList=null;
		perList= personalLoansRepo.findAll(Sort.by(Order.desc("createdAt")));
		
		return perList;
	}

}
