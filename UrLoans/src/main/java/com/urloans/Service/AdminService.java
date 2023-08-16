package com.urloans.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.urloans.Model.Admin;
import com.urloans.Repository.AdminRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo adminRepo;
	
	
	//Adding Admin's Data
	
	public Admin saveAdmin(Admin admin) {
		
		Admin ad=null;
//		return adminRepo.save(admin);
		try {
			ad=adminRepo.save(admin);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ad;
	}


	
//	public String  login1(Admin admin) {
//		
////		APIResponse apiResponse=new APIResponse();
//		
//		Admin loginInfo= adminRepo.findByEmailAndPassword(admin.getEmail(),admin.getPassword());
//		
//			if(loginInfo==null) {
////				apiResponse.setData("Admin login Failed");
//				return "Email Or Password are Invalid";
//			}else {
////				apiResponse.setData("Admin Logged in");
//				return "Admin Logged in";
//			}
////		return apiResponse;
//	}
	

	
	public Admin login(Admin admin) {
		Admin ad=null;
		
//		Admin loginInfo=adminRepo.findByEmailAndPassword(admin.getEmail(),admin.getPassword());
		
//		try{
			ad=adminRepo.findByEmailAndPassword(admin.getEmail(),admin.getPassword());
//		}catch(Exception e) {
//			e.printStackTrace();
//			
//		}
		return ad;
	}
}
