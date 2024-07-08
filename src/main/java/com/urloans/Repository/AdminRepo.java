package com.urloans.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.urloans.Model.Admin;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

	Admin findByEmailAndPassword(String email, String password);


    Optional<Admin> findByEmail(String email);
}
