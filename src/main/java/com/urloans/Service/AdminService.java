package com.urloans.Service;

import com.urloans.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.urloans.Model.Admin;
import com.urloans.Repository.AdminRepo;

@Service
public class AdminService {


	private AdminRepo adminRepo;

	@Autowired
	public AdminService(AdminRepo adminRepo) {
		super();
		this.adminRepo = adminRepo;
	}
	//Adding Admin's Data
	
	public Admin saveAdmin(Admin admin) {
//		Admin ad=null;
		if(admin !=null) {
			if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
				throw new BadRequestException("Email should not be empty or null");
			} else if (admin.getPassword().isEmpty()) {
				throw new BadRequestException("Password should not be Empty");
			} else if (admin.getUserName() == null || admin.getUserName().isEmpty()) {
				throw new BadRequestException("User name should not be empty or null");
			} else {
				return adminRepo.save(admin);
			}
		}
		throw new BadRequestException("Admin User details should not be null");
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
