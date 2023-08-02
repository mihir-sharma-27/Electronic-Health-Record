package com.stackroute.EHR.controller;

import com.stackroute.EHR.model.PatientHealthDetails;
import com.stackroute.EHR.model.Vaccination;
import com.stackroute.EHR.service.VaccinationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VaccinationControllerTest {
    @Mock
    private VaccinationService vaccinationService;;
    @InjectMocks
    private VaccinationController vaccinationController;

    @Test
    public void testAddVaccination() throws Exception {
        Vaccination vaccination = new Vaccination(1L, "vaccineName", "23-07-2022","2","07-05-2023");
        when(vaccinationService.saveVaccination(any(Vaccination.class))).thenReturn(vaccination);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(vaccinationController).build();
        mockMvc.perform(post("/vaccination")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"vaccinationName\":\"vaccinationName\",\"vaccinationDate\":\"23-07-2022\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllPatientHealthDetails() throws Exception {
        Vaccination vaccination1 = new Vaccination(1L, "vaccineName", "23-07-2022","2","07-05-2023");
        Vaccination vaccination2 = new Vaccination(2L, "vaccineName2", "24-07-2022","3","08-05-2023");
        // Set properties for patientHealthDetails1 and patientHealthDetails2

        List<Vaccination> vaccinationList = Arrays.asList(vaccination1,vaccination2);

        when(vaccinationService.getAllVaccinations()).thenReturn(vaccinationList);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(vaccinationController).build();

        mockMvc.perform(get("/vaccinations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    public void testUpdatePatientHealthDetails() throws Exception {
        Long id = 1L;
        Vaccination vaccination1 = new Vaccination(1L, "vaccineName1", "23-07-2022","2","07-05-2023");


        when(vaccinationService.updateVaccination( any(Vaccination.class)))
                .thenReturn(vaccination1);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(vaccinationController).build();

        mockMvc.perform(put("/vaccination", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"vaccinationName\":\"vaccinationName1\",\"vaccinationDate\":\"23-07-2022\"}"))
                .andExpect(status().isOk());

    }




}
