package com.stackroute.EHR.controller;

import java.util.HashMap;
import java.util.Map;

import com.stackroute.EHR.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.EHR.model.Authentication;
import com.stackroute.EHR.repository.AuthenticationRepository;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    //Autowire patient repository
    @Autowired
    private AuthenticationService authenticationService;


    //create api to register new patient, autogenerate patient id and check patient email is unique
    @PostMapping("/register")
    public ResponseEntity<Authentication> registerPatient(@RequestBody Authentication authentication) {
        try{
            return ResponseEntity.ok(authenticationService.register(authentication));
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    //create api to login patient
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginPatient(@RequestBody Authentication authentication) {
     try{
         return ResponseEntity.ok(authenticationService.login(authentication));
     }
     catch (Exception e){
         return ResponseEntity.notFound().build();
     }
    }

}
