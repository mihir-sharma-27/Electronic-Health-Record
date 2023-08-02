package com.stackroute.EHR.controller;

import com.stackroute.EHR.model.PatientHealthDetails;

import com.stackroute.EHR.service.PatientHealthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PatientHealthDetailsController {

    @Autowired
    private PatientHealthDetailsService patientHealthDetailsService;
    //create a api endpoint using patient health details service to add a patient health details and return response entity
    @PostMapping("/patientHealthDetails")
    public ResponseEntity<PatientHealthDetails> addPatientHealthDetails(@RequestBody PatientHealthDetails patientHealthDetails){
        return ResponseEntity.ok(patientHealthDetailsService.addPatientHealthDetails(patientHealthDetails));
    }

    //create a api endpoint using patient health details service to get all the patient health details and return response entity
    @GetMapping("/patientHealthDetails")
    public ResponseEntity<List<PatientHealthDetails>> getAllPatientHealthDetails(){
        return ResponseEntity.ok(patientHealthDetailsService.getAllPatientHealthDetails());
    }

    //create a api endpoint using patient health details service to get a patient health details by id and return response entity
    @GetMapping("/patientHealthDetails/{id}")
    public ResponseEntity<PatientHealthDetails> getPatientHealthDetailsById(@PathVariable Long id){
        //use try catch block to handle exception
        try{
            return ResponseEntity.ok(patientHealthDetailsService.getPatientHealthDetailsById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    //create an api endpoint to update patient health details by id and return response entity
    @PutMapping("/patientHealthDetails/{id}")
    public ResponseEntity<PatientHealthDetails> updatePatientHealthDetails(@PathVariable Long id, @RequestBody PatientHealthDetails patientHealthDetails){
        //use try catch block to handle exception
        try{
            return ResponseEntity.ok(patientHealthDetailsService.updatePatientHealthDetails(id, patientHealthDetails));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //create an api endpoint to delete patient health details by id and return response entity
    @DeleteMapping("/patientHealthDetails/{id}")
    public ResponseEntity<Boolean> deletePatientHealthDetails(@PathVariable Long id){
        //use try catch block to handle exception
        try{
            return ResponseEntity.ok(patientHealthDetailsService.deletePatientHealthDetails(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
