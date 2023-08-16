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

import com.urloans.Model.Queries;
import com.urloans.Service.QueriesService;

@RestController
@RequestMapping("/queries")
@CrossOrigin
public class QueriesController {
	
	@Autowired
	private QueriesService queryService;
	
	//Adding Queries
	@PostMapping("/add")
	public ResponseEntity<Queries> add(@RequestBody Queries query){
		 Queries qr=null;
		 try {
			 qr=queryService.addQuery(query);
			 return ResponseEntity.status(HttpStatus.CREATED).body(qr);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 }
		
	}
	
	//Fetching All the queries
	@GetMapping("/getAll")
	public ResponseEntity<List<Queries>> getAll(){
		List<Queries> ql=null;
		try {
			ql=queryService.getAll();
			return ResponseEntity.status(HttpStatus.FOUND).body(ql);
		}catch(Exception e) {
			e.printStackTrace();
			ql=queryService.getAll();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ql);
		}
		
	}

}
