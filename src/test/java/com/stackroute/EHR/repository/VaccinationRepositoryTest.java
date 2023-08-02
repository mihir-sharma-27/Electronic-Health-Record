package com.stackroute.EHR.repository;

import com.stackroute.EHR.model.Medication;
import com.stackroute.EHR.model.PatientHealthDetails;
import com.stackroute.EHR.model.Vaccination;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VaccinationRepositoryTest {
    @Autowired
    private VaccinationRepository vaccinationRepository;

    private Vaccination vaccination;

    @BeforeEach
    void setUp() {
        // Create a sample PatientHealthDetails object for testing
        vaccination = new Vaccination();
        vaccination.setVaccinationName("Covid");
        vaccination.setVaccinationDate("12/12/2020");
        vaccination.setVaccinationDate("12-04-2023");
        vaccination.setPatientId("2");
        vaccination.setNextDueDate("13-07-2023");
    }
    @AfterEach
    void tearDown() {
        // Clean up the test data after each test
        vaccinationRepository.deleteAll();
    }

    @Test
    void testSavePatientHealthDetails() {
        // Act
        Vaccination vaccination1 = vaccinationRepository.save(vaccination);

        // Assert
        assertNotNull(vaccination1);
        assertNotNull(vaccination1.getPatientId()); // Ensure that the id is generated
        assertEquals(vaccination.getVaccinationDate(), vaccination1.getVaccinationDate());


    }

    @Test
    void testFindPatientHealthDetailsById() {
        // Arrange
        Vaccination vaccination1 = vaccinationRepository.save(vaccination);

        // Act
        Optional<Vaccination> foundVaccine = vaccinationRepository.findById(vaccination1.getVaccinationId());

        // Assert
        assertTrue(foundVaccine.isPresent());
        assertEquals(vaccination1, foundVaccine.get());
    }

    @Test
    void testFindAllPatientHealthDetails() {
        // Arrange
        Vaccination savedVaccination = vaccinationRepository.save(vaccination);

        // Act
        List<Vaccination> foundVaccination = vaccinationRepository.findAll();

        // Assert
        assertTrue(foundVaccination.size() > 0);
        assertEquals(savedVaccination, foundVaccination.get(0));
    }

    @Test
    void testDeletePatientHealthDetails() {
        // Arrange
        Vaccination savedVaccination = vaccinationRepository.save(vaccination);

        // Act
        vaccinationRepository.deleteById(savedVaccination.getVaccinationId());

        // Assert
        assertFalse(vaccinationRepository.findById(savedVaccination.getVaccinationId()).isPresent());
    }


}
