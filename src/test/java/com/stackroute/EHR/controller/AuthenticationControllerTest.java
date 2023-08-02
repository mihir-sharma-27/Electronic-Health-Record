package com.stackroute.EHR.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.stackroute.EHR.model.Authentication;
import com.stackroute.EHR.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
    @InjectMocks
    private AuthenticationController authenticationController;
    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void testRegisterPatient_WithUniqueEmail_ShouldReturn200() throws Exception {
        Authentication authentication= new Authentication("test@example.com","password");
        when(authenticationService.register(any(Authentication.class))).thenReturn(authentication);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk()).andReturn();
        verify(authenticationService, times(1)).register(authentication);
    }
    @Test
    public void testLoginPatient_WithValidCredentials() throws Exception {
        Authentication authentication= new Authentication("test@example.com","password");
        Map<String,String> response = new HashMap<>();
        response.put("message", "Login successful.");
        when(authenticationService.login(any(Authentication.class))).thenReturn(response);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk()).andReturn();
        verify(authenticationService, times(1)).login(authentication);
    }
}
