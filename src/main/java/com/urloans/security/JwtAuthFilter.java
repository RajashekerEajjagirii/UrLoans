package com.urloans.security;

import com.urloans.Config.AuthenticateUserDetailsService;
import com.urloans.exception.BadRequestException;
import com.urloans.util.UserByEmail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserByEmail userByEmail;

    @Autowired
    private AuthenticateUserDetailsService authenticateUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JWTAut Filter doFilterInternal Comming");
        //get token from Header
        String token=getToken(request);
        // Verifying token is valid or not
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
            String email=jwtTokenProvider.getEmailFromToken(token);
            UserDetails userDetails=authenticateUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        System.out.println(" Befor filter chain Bearer "+token);
        //sending filterchain
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")) {
            token=token.substring(7);
        }
        return token;
    }
}
