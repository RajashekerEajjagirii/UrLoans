package com.urloans.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urloans.Model.PersonalLoans;

@Repository
public interface PersonalLoansRepo extends JpaRepository<PersonalLoans, Integer> {

	
}
