package com.urloans.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urloans.Model.BusinessLoans;
import com.urloans.Service.BusinessLoansService;

@RestController
@RequestMapping("/api/businessloans")
@CrossOrigin
public class BusinessLoansControlller {
	
	@Autowired
	private BusinessLoansService businessLoanService;
	
	//Adding Business Data
	@PostMapping
	public ResponseEntity<BusinessLoans> add(@RequestBody BusinessLoans businessLoans) {
			BusinessLoans bl=null;
			try {
				bl=businessLoanService.saveBusinessLoans(businessLoans);
				return ResponseEntity.status(HttpStatus.CREATED).body(bl);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		
	}
	
	@GetMapping
	public ResponseEntity<List<BusinessLoans>> getAll(){
			List<BusinessLoans> bl=null;
			try {
				bl=this.businessLoanService.getAll();
				System.out.println(bl);
//				return ResponseEntity.status(HttpStatus.FOUND).body(bl);
				return ResponseEntity.of(Optional.of(bl));
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		
	}

}
