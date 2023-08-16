package com.urloans.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.urloans.Model.BusinessLoans;
import com.urloans.Repository.BusinessLoansRepo;

@Service
public class BusinessLoansService {

	@Autowired
	private BusinessLoansRepo businessRepo;
	
	//Adding Business Loans Data
	public BusinessLoans saveBusinessLoans(BusinessLoans businessLoans) {
		BusinessLoans bl=null;
		bl= businessRepo.save(businessLoans);
		return bl;
	}

	public List<BusinessLoans> getAll() {
		List<BusinessLoans> bl=null;
		bl= businessRepo.findAll(Sort.by(Order.desc("createdAt")));
		return bl;
	}
}
