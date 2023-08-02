package com.stackroute.EHR.jwtConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.EHR.model.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class SecurityTokenGeneratorImp implements SecurityTokenGenerator{
    //add the secret key here from application.properties file
    @Value("${jwt.secret}")
    private String SECRET;
    //add expiration time here from application.properties file
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    @Override
    public Map<String, String> generateToken(Authentication authentication) {
        Map<String, String> map = new HashMap<>();
        long currentTimeInMilli = System.currentTimeMillis();
        String jwtToken = Jwts.builder()
                .setSubject(authentication.getEmail())
                .setIssuedAt(new Date(currentTimeInMilli))
                .signWith(SignatureAlgorithm.HS512, SECRET)
               .setExpiration(new Date(currentTimeInMilli+EXPIRATION_TIME))     //token will be valid for 20 minutes only.
                .compact();
        map.put("token", jwtToken);
        map.put("message", "User successfully logged in");
                              
        return map;
    }
    
}
