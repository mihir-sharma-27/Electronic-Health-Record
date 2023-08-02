package com.stackroute.EHR.repository;

import com.stackroute.EHR.model.PatientHealthDetails;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//create JPA repository layer for patient health details
@Repository
public interface PatientHealthDetailsRepository extends JpaRepository<PatientHealthDetails, Long> {
  
}

