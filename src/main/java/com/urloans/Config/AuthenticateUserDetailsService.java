package com.urloans.Config;

import com.urloans.Model.Admin;
import com.urloans.Repository.AdminRepo;
import com.urloans.exception.UrLoansNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticateUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UrLoansNotFoundException {
        log.info("loadUserByUsername Comming");
        Admin admin=adminRepo.findByEmail(email).orElseThrow(()->new UrLoansNotFoundException("Invalid Email: "+email));
        Set<String> roles=new HashSet<>();
        roles.add("ROLE_ADMIN");
        log.info("Email: {} Username: {}", admin.getEmail(), admin.getUserName());
        return new User(admin.getEmail(),admin.getPassword(),getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
