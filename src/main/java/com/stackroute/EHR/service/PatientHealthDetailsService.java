package com.stackroute.EHR.service;
import com.stackroute.EHR.exception.NotFoundException;
import com.stackroute.EHR.model.PatientHealthDetails;
import com.stackroute.EHR.repository.PatientHealthDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientHealthDetailsService {
    //Autowire the PatiendHealthDetailsRepository here
    @Autowired
    private PatientHealthDetailsRepository patiendHealthDetailsRepository;
    //constructor
    public PatientHealthDetailsService(PatientHealthDetailsRepository patiendHealthDetailsRepository) {
        this.patiendHealthDetailsRepository = patiendHealthDetailsRepository;
    }

    //crete a method to get all the patient health details use exception handling
    public List<PatientHealthDetails> getAllPatientHealthDetails(){
        return patiendHealthDetailsRepository.findAll();
    }

    //crete a method to get a patient health details by id use exception handling
    public PatientHealthDetails getPatientHealthDetailsById(Long id){
        return patiendHealthDetailsRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient Health Details not found"));
    }

    //create a method to add a patient health details use exception handling and auto generate patient id
    public PatientHealthDetails addPatientHealthDetails(PatientHealthDetails patientHealthDetails){
        return patiendHealthDetailsRepository.save(patientHealthDetails);
    }

    //crete a method to update a patient health details by id use exception handling
    public PatientHealthDetails updatePatientHealthDetails(Long id,PatientHealthDetails patientHealthDetails){
        System.out.println(patientHealthDetails.toString());
        PatientHealthDetails existingPatientHealthDetails = patiendHealthDetailsRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient Health Details not found"));
        existingPatientHealthDetails.setPatientHeight(patientHealthDetails.getPatientHeight());
        existingPatientHealthDetails.setPatientWeight(patientHealthDetails.getPatientWeight());
        existingPatientHealthDetails.setPatientBloodGroup(patientHealthDetails.getPatientBloodGroup());
        existingPatientHealthDetails.setPatientAllergies(patientHealthDetails.getPatientAllergies());
        existingPatientHealthDetails.setPatientHealthConditions(patientHealthDetails.getPatientHealthConditions());
        existingPatientHealthDetails.setPatientMedications(patientHealthDetails.getPatientMedications());
        return patiendHealthDetailsRepository.save(existingPatientHealthDetails);
    }

    //crete a method to delete a patient health details by id use exception handling
    public boolean deletePatientHealthDetails(Long id){
        patiendHealthDetailsRepository.deleteById(id);
        return true;
    }
}
