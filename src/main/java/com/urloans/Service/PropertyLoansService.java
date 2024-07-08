package com.urloans.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.urloans.Model.PropertyLoans;
import com.urloans.Repository.PropertyLoansRepo;

@Service
public class PropertyLoansService {
	
	@Autowired
	private PropertyLoansRepo propertyRepo;
	
	 
	// Adding property(LAP) Loans Data
	
	public PropertyLoans savePropertyLoans(PropertyLoans propertyLoans) {
		PropertyLoans lap=null;
		lap= propertyRepo.save(propertyLoans);
		return lap;
	}


	public List<PropertyLoans> getAll() {
		List<PropertyLoans> list=null;
		
		list=propertyRepo.findAll(Sort.by(Order.desc("createdAt")));
		return list;
	}

}
