package com.stackroute.EHR.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.EHR.exception.NotFoundException;
import com.stackroute.EHR.model.PatientHealthDetails;
import com.stackroute.EHR.repository.PatientHealthDetailsRepository;


public class PatientHealthDetailsServiceTest {

    @Mock
    private PatientHealthDetailsRepository patientHealthDetailsRepository;

    @InjectMocks
    private PatientHealthDetailsService patientHealthDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPatientHealthDetails() {
        List<PatientHealthDetails> patientHealthDetailsList = new ArrayList<>();
        // Add some patient health details to the list

        when(patientHealthDetailsRepository.findAll()).thenReturn(patientHealthDetailsList);

        List<PatientHealthDetails> result = patientHealthDetailsService.getAllPatientHealthDetails();

        assertEquals(patientHealthDetailsList, result);
    }

    @Test
    public void testGetPatientHealthDetailsById() {
        Long id = 1L;
        PatientHealthDetails patientHealthDetails = new PatientHealthDetails();
        // Set patient health details properties

        when(patientHealthDetailsRepository.findById(id)).thenReturn(Optional.of(patientHealthDetails));

        PatientHealthDetails result = patientHealthDetailsService.getPatientHealthDetailsById(id);

        assertEquals(patientHealthDetails, result);
    }

    @Test
    public void testGetPatientHealthDetailsById_NotFound() {
        Long id = 1L;

        when(patientHealthDetailsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> patientHealthDetailsService.getPatientHealthDetailsById(id));
    }

    @Test
    public void testAddPatientHealthDetails() {
        PatientHealthDetails patientHealthDetails = new PatientHealthDetails();
        // Set patient health details properties

        when(patientHealthDetailsRepository.save(patientHealthDetails)).thenReturn(patientHealthDetails);

        PatientHealthDetails result = patientHealthDetailsService.addPatientHealthDetails(patientHealthDetails);

        assertEquals(patientHealthDetails, result);
    }

    @Test
    public void testUpdatePatientHealthDetails() {
        Long id = 1L;
        PatientHealthDetails existingPatientHealthDetails = new PatientHealthDetails();
        // Set existing patient health details properties
        PatientHealthDetails updatedPatientHealthDetails = new PatientHealthDetails();
        // Set updated patient health details properties

        when(patientHealthDetailsRepository.findById(id)).thenReturn(Optional.of(existingPatientHealthDetails));
        when(patientHealthDetailsRepository.save(existingPatientHealthDetails)).thenReturn(updatedPatientHealthDetails);

        PatientHealthDetails result = patientHealthDetailsService.updatePatientHealthDetails(id, updatedPatientHealthDetails);

        assertEquals(updatedPatientHealthDetails, result);
    }

    @Test
    public void testUpdatePatientHealthDetails_NotFound() {
        Long id = 1L;
        PatientHealthDetails updatedPatientHealthDetails = new PatientHealthDetails();
        // Set updated patient health details properties

        when(patientHealthDetailsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> patientHealthDetailsService.updatePatientHealthDetails(id, updatedPatientHealthDetails));
    }

    @Test
    public void testDeletePatientHealthDetails() {
        Long id = 1L;

        patientHealthDetailsService.deletePatientHealthDetails(id);

        // Verify that the deleteById method was called with the correct ID
        verify(patientHealthDetailsRepository).deleteById(id);
    }
}

