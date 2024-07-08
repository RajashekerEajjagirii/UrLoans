package com.urloans.Config;

import com.urloans.security.JwtAuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    //Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Password Encoder Comming");
        return new BCryptPasswordEncoder();
    }

    //Security Filter Bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("Filter Chain Comming");
                 http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize->authorize
                                .requestMatchers("/api/admin/**").permitAll()
                                .anyRequest().authenticated()
                                )
                       .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                       .authenticationProvider(authenticationProvider());

//                       .formLogin(login->login.loginPage("/signin")
//                               .usernameParameter("email")
//                               .defaultSuccessUrl("/",true)
//                               .permitAll())
//                       .logout(logout->logout.logoutUrl("/signout").permitAll())
//
                       http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                     return   http.build();


    }

    @Bean
    public AuthenticationManager authentication(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.info("Authentication Mangaer Comming");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        log.info("Auth Provider Comming");
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        log.info("UserDetails Service Comming");
        return new AuthenticateUserDetailsService();
    }


}
