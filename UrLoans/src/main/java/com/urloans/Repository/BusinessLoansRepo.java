package com.urloans.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urloans.Model.BusinessLoans;

@Repository
public interface BusinessLoansRepo  extends JpaRepository<BusinessLoans, Integer>{

}
