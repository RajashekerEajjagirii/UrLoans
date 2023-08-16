package com.urloans.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urloans.Model.Admin;
import com.urloans.Service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/add")
	public ResponseEntity<Admin> add(@RequestBody Admin admin) {
		Admin ad=null;
		
		try {
			ad=adminService.saveAdmin(admin);
//			return ResponseEntity.of(Optional.of(ad));
			return ResponseEntity.status(HttpStatus.CREATED).body(ad);
			
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
//	@PostMapping("/login1")
//	public String login1(@RequestBody Admin admin){
//		
//	 String msg=adminService.login1(admin);
//		
//		return msg;
//		
//	}
	
	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody Admin admin){
		 Admin  ad=null;
		try {
			 ad=this.adminService.login(admin);
			System.out.println(ad);
			return ResponseEntity.of(Optional.of(ad));	
//			return ResponseEntity.status(HttpStatus.FOUND).body(ad);	
			
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
}
