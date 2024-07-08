package com.urloans.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urloans.Model.PropertyLoans;

@Repository
public interface PropertyLoansRepo extends JpaRepository<PropertyLoans, Integer>{
	
	

}
