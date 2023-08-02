package com.stackroute.EHR.repository;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stackroute.EHR.model.Medication;
import com.stackroute.EHR.model.PatientHealthDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class PatientHealthDetailsRepositoryTest {

    @Autowired
    private PatientHealthDetailsRepository patientHealthDetailsRepository;

    private PatientHealthDetails testHealthDetails;

    @BeforeEach
    void setUp() {
        // Create a sample PatientHealthDetails object for testing
        testHealthDetails = new PatientHealthDetails();
        testHealthDetails.setPatientId(1L);
        testHealthDetails.setPatientBloodGroup("A+");
        testHealthDetails.setPatientHeight(175);
        testHealthDetails.setPatientWeight(70);
        testHealthDetails.setPatientAllergies(new ArrayList<>(List.of("Dust", "Pollen")));
        testHealthDetails.setPatientHealthConditions(new ArrayList<>(List.of("Asthma", "Diabetes")));
        testHealthDetails.setPatientMedications(new ArrayList<>(List.of(new Medication("aspirin", "10mg", "once daily"), new Medication("paracetamol", "10mg", "once daily"))));
    }

    @AfterEach
    void tearDown() {
        // Clean up the test data after each test
        patientHealthDetailsRepository.deleteAll();
    }

    @Test
    void testSavePatientHealthDetails() {
        // Act
        PatientHealthDetails savedHealthDetails = patientHealthDetailsRepository.save(testHealthDetails);

        // Assert
        assertNotNull(savedHealthDetails);
        assertNotNull(savedHealthDetails.getPatientId()); // Ensure that the id is generated
        assertEquals(testHealthDetails.getPatientBloodGroup(), savedHealthDetails.getPatientBloodGroup());
        assertEquals(testHealthDetails.getPatientHeight(), savedHealthDetails.getPatientHeight());

    }

    @Test
    void testFindPatientHealthDetailsById() {
        // Arrange
        PatientHealthDetails savedHealthDetails = patientHealthDetailsRepository.save(testHealthDetails);

        // Act
        Optional<PatientHealthDetails> foundHealthDetails = patientHealthDetailsRepository.findById(savedHealthDetails.getPatientId());

        // Assert
        assertTrue(foundHealthDetails.isPresent());
        assertEquals(savedHealthDetails, foundHealthDetails.get());
    }

 @Test
 void testFindAllPatientHealthDetails() {
     // Arrange
     PatientHealthDetails savedHealthDetails = patientHealthDetailsRepository.save(testHealthDetails);

     // Act
     List<PatientHealthDetails> foundHealthDetails = patientHealthDetailsRepository.findAll();

     // Assert
     assertTrue(foundHealthDetails.size() > 0);
     assertEquals(savedHealthDetails, foundHealthDetails.get(0));
 }

    @Test
    void testDeletePatientHealthDetails() {
        // Arrange
        PatientHealthDetails savedHealthDetails = patientHealthDetailsRepository.save(testHealthDetails);

        // Act
        patientHealthDetailsRepository.deleteById(savedHealthDetails.getPatientId());

        // Assert
        assertFalse(patientHealthDetailsRepository.findById(savedHealthDetails.getPatientId()).isPresent());
    }
// test update
    @Test
    void testUpdatePatientHealthDetails() {
        // Arrange
        PatientHealthDetails savedHealthDetails = patientHealthDetailsRepository.save(testHealthDetails);
        savedHealthDetails.setPatientBloodGroup("B+");
        savedHealthDetails.setPatientHeight(180);
        savedHealthDetails.setPatientWeight(80);
        savedHealthDetails.setPatientAllergies(new ArrayList<>(List.of("Dust", "Pollen")));
        savedHealthDetails.setPatientHealthConditions(new ArrayList<>(List.of("Asthma", "Diabetes")));
        savedHealthDetails.setPatientMedications(new ArrayList<>(List.of(new Medication("aspirin", "10mg", "once daily"), new Medication("paracetamol", "10mg", "once daily"))));

        // Act
        PatientHealthDetails updatedHealthDetails = patientHealthDetailsRepository.save(savedHealthDetails);

        // Assert
        assertNotNull(updatedHealthDetails);
        assertEquals(savedHealthDetails.getPatientBloodGroup(), updatedHealthDetails.getPatientBloodGroup());
        assertEquals(savedHealthDetails.getPatientHeight(), updatedHealthDetails.getPatientHeight());
        assertEquals(savedHealthDetails.getPatientWeight(), updatedHealthDetails.getPatientWeight());
        assertEquals(savedHealthDetails.getPatientAllergies(), updatedHealthDetails.getPatientAllergies());
        assertEquals(savedHealthDetails.getPatientHealthConditions(), updatedHealthDetails.getPatientHealthConditions());
        assertEquals(savedHealthDetails.getPatientMedications(), updatedHealthDetails.getPatientMedications());
    }



}

