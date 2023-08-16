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

import com.urloans.Model.PropertyLoans;
import com.urloans.Service.PropertyLoansService;

@RestController
@RequestMapping("/propertyloans")
@CrossOrigin
public class PropertyLoansController {
	
	@Autowired
	private PropertyLoansService propertyLoansService;
	
	
	//Adding Loan against property Data
	@PostMapping("/add")
	public ResponseEntity<PropertyLoans> add(@RequestBody PropertyLoans propertyLoans) {
			PropertyLoans lap=null;
			try {
				lap=propertyLoansService.savePropertyLoans(propertyLoans);
				return ResponseEntity.status(HttpStatus.CREATED).body(lap);
				}catch(Exception e){
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
				
	}
	
	
	//fetching all LAP users list
	@GetMapping("/getAll")
	public ResponseEntity<List<PropertyLoans>> getAll(){
		List<PropertyLoans> lap=null;
		try {
			lap= propertyLoansService.getAll();
			return ResponseEntity.status(HttpStatus.FOUND).body(lap);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
