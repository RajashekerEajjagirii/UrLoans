package com.urloans.security;

import com.urloans.exception.BadRequestException;
import com.urloans.exception.UrLoansNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    public static  final String SECRET = "oboe3lJIW65lFuCErYdnZ8tlGRIobG+03g7lyjRJZHoCgInlkwzTyWA7NfFRt0p0118/";

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate=new Date(currentDate.getTime()+1000*60*60*2);
        log.info("Generate Token Comming");
        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
        return token;

    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Getting Email from Token
    public String getEmailFromToken(String token) {
        Claims claims= Jwts.parser().setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private Boolean isTokenExpired(String token) throws BadRequestException {
        Claims claims= Jwts.parser().setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getEmailFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }

    //validate token
    public boolean validateToken(String token)  {
        try{
            Jwts.parser().setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            throw new BadRequestException("Invalid token ");
        }
    }
}
