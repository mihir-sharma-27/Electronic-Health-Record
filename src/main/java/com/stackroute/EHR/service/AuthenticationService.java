package com.stackroute.EHR.service;

import com.stackroute.EHR.jwtConfig.SecurityTokenGenerator;
import com.stackroute.EHR.model.Authentication;
import com.stackroute.EHR.repository.AuthenticationRepository;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class AuthenticationService {
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Autowired
    private SecurityTokenGenerator tokenGenerator;


    public Authentication register(Authentication authentication){
        //encrypt  password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        authentication.setPassword(passwordEncoder.encode(authentication.getPassword()));

        if(authenticationRepository.findById(authentication.getEmail()).isEmpty()) {
            return authenticationRepository.save(authentication);

        }
        else {
            throw new RuntimeException("Email already exists");
        }

    }
    public Map<String,String> login(Authentication authentication){
        Map<String,String> map=new HashMap<>();
        Authentication existinguser = authenticationRepository.findById(authentication.getEmail()).get();
        if(existinguser!=null){
            BCryptPasswordEncoder passwordEncoder=new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            if(passwordEncoder.matches(authentication.getPassword(),existinguser.getPassword())){
                return tokenGenerator.generateToken(existinguser);
            }
            else{
                map.put("message","Invalid Password");
                return map;
            }
        }
        else{
            map.put("message","Invalid Email");
            return map;
        }
    }
}
