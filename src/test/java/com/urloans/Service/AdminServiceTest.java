package com.urloans.Service;

import com.urloans.Model.Admin;
import com.urloans.Repository.AdminRepo;
import com.urloans.exception.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminRepo adminRepo;

    @Test
    @DisplayName("Test should pass when added admin user")
    void testAddAdminUser() {
        AdminService adminService = new AdminService(adminRepo);
        Admin admin = new Admin(12,"Raj","raj@gmail.com","raj12",new Date());
        Mockito.when(adminRepo.save(admin)).thenReturn(admin);
        Admin result = adminService.saveAdmin(admin);
        Assertions.assertThat(result.getId()).isEqualTo(admin.getId());
        Assertions.assertThat(result.getUserName()).isEqualTo(admin.getUserName());
    }

    @Test
    @DisplayName("Test should pass when It throw BadRequestException")
    void testWithEmailHasNullValue() {
        AdminService adminService = new AdminService(adminRepo);
        Admin admin = new Admin(12,"Raj",null,"12",new Date());
        assertThatThrownBy(()->{
            adminService.saveAdmin(admin);
        }).isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email should not be empty or null");

    }

    @Test
    @DisplayName("TestWithUserHasNullValue")
    void testWithUserHasNullValue() {
        AdminService adminService = new AdminService(adminRepo);
        Admin admin = new Admin(12,null,"raj@gmail.com","123",new Date());
        assertThatThrownBy(()->{
            adminService.saveAdmin(admin);
        }).isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User name should not be empty or null");

    }

    @Test
    @DisplayName("TestWithPasswordHasEmptyValue")
    void testWithPasswordHasEmptyValue() {
        AdminService adminService = new AdminService(adminRepo);
        Admin admin = new Admin(12,"Raj","raj@gmail.com","",new Date());
        assertThatThrownBy(()->{
            adminService.saveAdmin(admin);
        }).isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Password should not be Empty");

    }

}