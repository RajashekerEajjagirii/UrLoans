package com.urloans.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urloans.Model.HomeLoans;

@Repository
public interface HomeLoansRepo extends JpaRepository<HomeLoans, Integer> {
	
	

}
