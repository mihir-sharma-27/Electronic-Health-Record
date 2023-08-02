package com.stackroute.EHR.service;

import com.stackroute.EHR.exception.NotFoundException;
import com.stackroute.EHR.model.PatientHealthDetails;
import com.stackroute.EHR.model.Vaccination;
import com.stackroute.EHR.repository.PatientHealthDetailsRepository;
import com.stackroute.EHR.repository.VaccinationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VaccinationServiceTest {
    @Mock
    private VaccinationRepository vaccinationRepository;

    @InjectMocks
    private VaccinationService vaccinationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPatientHealthDetails() {
        List<Vaccination> vaccinationList = new ArrayList<>();
        Vaccination vaccination1 = new Vaccination(1L, "vaccineName", "23-07-2022","2","07-05-2023");
        vaccinationList.add(vaccination1);
        when(vaccinationRepository.findAll()).thenReturn(vaccinationList);

        List<Vaccination> result = vaccinationService.getAllVaccinations();

        assertEquals(vaccinationList, result);
    }

    @Test
    public void testGetPatientHealthDetailsById() {
        Long id = 1L;
        Vaccination vaccination1 = new Vaccination(1L, "vaccineName", "23-07-2022","2","07-05-2023");


        when(vaccinationRepository.findById(id)).thenReturn(Optional.of(vaccination1));

       Vaccination result = vaccinationService.getVaccineByID(id);

        assertEquals(vaccination1, result);
    }



    @Test
    public void testAddPatientHealthDetails() {
        Vaccination vaccination1 = new Vaccination(1L, "vaccineName", "23-07-2022","2","07-05-2023");

        when(vaccinationRepository.save(vaccination1)).thenReturn(vaccination1);

        Vaccination result = vaccinationService.saveVaccination(vaccination1);

        assertEquals(vaccination1, result);
    }

    @Test
    public void testUpdatePatientHealthDetails() {
        Long id = 1L;
        Vaccination vaccination1 = new Vaccination(1L, "vaccineName", "23-07-2022","2","07-05-2023");

        Vaccination updatedVaccination = new Vaccination(1L, "vaccineName1", "24-07-2022","2","07-05-2023");

        when(vaccinationRepository.findById(id)).thenReturn(Optional.of(vaccination1));
        when(vaccinationRepository.save(vaccination1)).thenReturn(updatedVaccination);

        Vaccination result = vaccinationService.updateVaccination(updatedVaccination);

        assertEquals(updatedVaccination, result);
    }



    @Test
    public void testDeletePatientHealthDetails() {
        Long id = 1L;

        vaccinationService.deleteVaccinationById(id);

        // Verify that the deleteById method was called with the correct ID
        verify(vaccinationRepository).deleteById(id);
    }
}
