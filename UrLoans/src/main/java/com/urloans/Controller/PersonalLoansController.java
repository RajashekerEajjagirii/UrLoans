package com.urloans.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urloans.Model.PersonalLoans;
import com.urloans.Service.PersonalLoansService;

@RestController
@RequestMapping("/personalLoans")
@CrossOrigin
public class PersonalLoansController {
	
	@Autowired
	private PersonalLoansService personalLoanService;
	
	//Adding PersonalLoans Data
	@PostMapping("/add")
	public ResponseEntity<PersonalLoans> add(@RequestBody PersonalLoans personalLoans) {
		
			PersonalLoans pl=null;
			try {
				pl=personalLoanService.savePersonalLoans(personalLoans);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(pl);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("PersonalLoansController.add()");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
//		personalLoanService.savePersonalLoans(personalLoans);
//		return " New user Added in Personal Loans";
		
	}
	
	
	// Fetching All Users Data
	@GetMapping("/getAll")
	public ResponseEntity<List<PersonalLoans>> getAllUsers(){
		List<PersonalLoans> personalList=null;
		try {
			personalList=personalLoanService.getAllUsers();
			return ResponseEntity.status(HttpStatus.FOUND).body(personalList);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

}
