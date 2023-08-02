package com.stackroute.EHR.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.stackroute.EHR.service.PatientHealthDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.EHR.model.PatientHealthDetails;


@ExtendWith(MockitoExtension.class)
public class PatientHealthDetailsControllerTest {

    @Mock
    private PatientHealthDetailsService patientHealthDetailsService;

    @InjectMocks
    private PatientHealthDetailsController patientHealthDetailsController;

    @Test
    public void testAddPatientHealthDetails() throws Exception {
        PatientHealthDetails patientHealthDetails = new PatientHealthDetails();
        // Set patientHealthDetails properties

        when(patientHealthDetailsService.addPatientHealthDetails(any(PatientHealthDetails.class)))
                .thenReturn(patientHealthDetails);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(patientHealthDetailsController).build();

        mockMvc.perform(post("/patientHealthDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"patientName\":\"Amit\",\"patientHeight\":12.00}"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetAllPatientHealthDetails() throws Exception {
        PatientHealthDetails patientHealthDetails1 = new PatientHealthDetails();
        PatientHealthDetails patientHealthDetails2 = new PatientHealthDetails();
        // Set properties for patientHealthDetails1 and patientHealthDetails2

        List<PatientHealthDetails> patientHealthDetailsList = Arrays.asList(patientHealthDetails1, patientHealthDetails2);

        when(patientHealthDetailsService.getAllPatientHealthDetails()).thenReturn(patientHealthDetailsList);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(patientHealthDetailsController).build();

        mockMvc.perform(get("/patientHealthDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetPatientHealthDetailsById() throws Exception {
        Long id = 1L;
        PatientHealthDetails patientHealthDetails = new PatientHealthDetails();
        // Set patientHealthDetails properties

        when(patientHealthDetailsService.getPatientHealthDetailsById(id)).thenReturn(patientHealthDetails);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(patientHealthDetailsController).build();

        mockMvc.perform(get("/patientHealthDetails/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePatientHealthDetails() throws Exception {
        Long id = 1L;
        PatientHealthDetails patientHealthDetails = new PatientHealthDetails();
        // Set patientHealthDetails properties

        when(patientHealthDetailsService.updatePatientHealthDetails(eq(id), any(PatientHealthDetails.class)))
                .thenReturn(patientHealthDetails);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(patientHealthDetailsController).build();

        mockMvc.perform(put("/patientHealthDetails/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"property\":\"value\"}"))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeletePatientHealthDetails() throws Exception {
        Long id = 1L;

        when(patientHealthDetailsService.deletePatientHealthDetails(id)).thenReturn(true);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(patientHealthDetailsController).build();

        mockMvc.perform(delete("/patientHealthDetails/{id}", id))
                .andExpect(status().isOk());

    }

}

