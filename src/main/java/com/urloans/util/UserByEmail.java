package com.urloans.util;

import com.urloans.Model.Admin;
import com.urloans.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserByEmail {

    @Autowired
    private AdminRepo adminRepo;

    //getting by email
    public Admin findByEmail(String email) {
        Optional<Admin> admin = adminRepo.findByEmail(email);
        return admin.orElseThrow(()-> new RuntimeException("Invalid email address"));
    }

}
