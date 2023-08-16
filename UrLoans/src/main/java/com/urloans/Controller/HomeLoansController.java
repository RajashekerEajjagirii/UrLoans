package com.urloans.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urloans.Model.HomeLoans;
import com.urloans.Repository.HomeLoansRepo;
import com.urloans.Service.HomeLoansService;

@RestController
@RequestMapping("/homeloans")
@CrossOrigin
public class HomeLoansController {
	
	@Autowired
	private HomeLoansService homeLoansService;
	
	@Autowired
	private HomeLoansRepo homeLoansRepo;
	
	// Adding HomeLoans Data
	@PostMapping("/add")
	public ResponseEntity<HomeLoans> add(@RequestBody HomeLoans homeLoans) {
			HomeLoans hl=null;
			try {
				hl=homeLoansService.saveHomeLoans(homeLoans);
				return ResponseEntity.status(HttpStatus.CREATED).body(hl);
			}catch(Exception e){
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}				
	}
	
	
	//Getting All Users Data
	@GetMapping("/getAll")
	public ResponseEntity<List<HomeLoans>> getAll(){
		List<HomeLoans> hl=null;
		try {
			hl=homeLoansService.getAll();
			return ResponseEntity.status(HttpStatus.FOUND).body(hl);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	
	//Get User by id
	@GetMapping("/getUser/{id}")
	public ResponseEntity<Optional<HomeLoans>> getUser(@PathVariable Integer id){
		Optional<HomeLoans> hl=null;
		try {
			hl=homeLoansService.getUserByid(id);
			return ResponseEntity.status(HttpStatus.FOUND).body(hl);
		}catch(Exception e) {
			e.printStackTrace();	
			hl=homeLoansService.getUserByid(id);
			System.out.println("user is not found for id= "+id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(hl);
		}
		
	}
	
	
	//Update User by id
	@PutMapping("/update/{id}")
	public ResponseEntity<HomeLoans> updateUser(@PathVariable int id,@RequestBody HomeLoans homeloans){
		HomeLoans hl=null;
		try {
			hl=homeLoansService.updateUser(id,homeloans);
			return ResponseEntity.status(HttpStatus.OK).body(hl);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	
	//Delete User by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id){
		Optional<HomeLoans> hl=homeLoansRepo.findById(id);
		 if(hl.isPresent()) {
			homeLoansRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
}
