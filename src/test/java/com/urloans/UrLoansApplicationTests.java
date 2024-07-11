package com.urloans;

import com.urloans.Model.Admin;
import com.urloans.Repository.AdminRepo;
import com.urloans.Service.AdminService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UrLoansApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private AdminService adminService;
//
//	@MockBean
//	private AdminRepo adminRepo;
//
//	@Test
//	@DisplayName("Test case for adding Admin User")
//	public void shouldAddAdminUser(){
//		Admin admin = new Admin(18,"Raj","raj@gmail.com","raj12",new Date());
//		when(adminRepo.save(admin)).thenReturn(admin);
//		assertEquals(admin,adminService.saveAdmin(admin));
//
//	}

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repo testH2Repo;

	@BeforeAll
	public static  void init(){
		restTemplate=new RestTemplate();
	}

	@BeforeEach
	public  void setUp(){
		baseUrl=baseUrl.concat(":").concat(port+"").concat("/api");
	}

	@Test
	public void testaddAdmin(){
		Admin admin=new Admin(18,"Raj","raj@gmail.com","raj12",new Date());
		Admin response= restTemplate.postForObject(baseUrl.concat("/admin/add"),admin,Admin.class);
		assertEquals(response.getId(),admin.getId());
		assertEquals(response.getUserName(),"Raj");

	}

}
