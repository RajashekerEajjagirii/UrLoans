package com.urloans.Controller;

import java.util.List;
import java.util.Optional;

import com.urloans.Repository.AdminRepo;
import com.urloans.dto.LoginRequest;
import com.urloans.dto.LoginResponse;
import com.urloans.exception.BadRequestException;
import com.urloans.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urloans.Model.Admin;
import com.urloans.Service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
@Slf4j
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AdminRepo adminRepo;
	
	@PostMapping("/add")
	public ResponseEntity<Admin> add(@RequestBody Admin admin) {
		Admin ad=null;
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			ad=adminService.saveAdmin(admin);
			return ResponseEntity.status(HttpStatus.CREATED).body(ad);

		
	}

	
	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody Admin admin){
		 Admin  ad=null;
		try {
			 ad=this.adminService.login(admin);
			System.out.println(ad);
			return ResponseEntity.of(Optional.of(ad));	
			//return ResponseEntity.status(HttpStatus.FOUND).body(ad);
			
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

	@PostMapping("/signin")
	public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest loginRequest){

		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
		);
		log.info("Authenticated user:  "+authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		//Getting JWT Token
		String token=jwtTokenProvider.generateToken(authentication);
		String email= jwtTokenProvider.getEmailFromToken(token);
		log.info("Email address from Token: "+email);
		Optional<Admin> admin=adminRepo.findByEmail(email);
		LoginResponse res=new LoginResponse();
		if(admin.isPresent()){
		  res.setId(admin.get().getId());
		  res.setEmail(admin.get().getEmail());
		  res.setUsername(admin.get().getUserName());
		  res.setToken(token);
		}
		log.info("Generated token:  "+token);
		return  new ResponseEntity<>(res,HttpStatus.OK);

	}
}
