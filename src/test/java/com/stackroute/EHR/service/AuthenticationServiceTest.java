package com.stackroute.EHR.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.stackroute.EHR.jwtConfig.SecurityTokenGenerator;
import com.stackroute.EHR.model.Authentication;
import com.stackroute.EHR.repository.AuthenticationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthenticationServiceTest {

    private AuthenticationService authenticationService;
    private AuthenticationRepository authenticationRepository;
    private SecurityTokenGenerator tokenGenerator;

    @BeforeEach
    void setUp() {
        // Create mock instances for the dependencies
        authenticationRepository = mock(AuthenticationRepository.class);
        tokenGenerator = mock(SecurityTokenGenerator.class);
        authenticationService = new AuthenticationService();
        authenticationService.setAuthenticationRepository(authenticationRepository);
        authenticationService.setTokenGenerator(tokenGenerator);
    }

    @Test
    void testRegister_Success() {
        // Arrange
        Authentication authentication = new Authentication();
        authentication.setEmail("test@example.com");
        authentication.setPassword("password");

        when(authenticationRepository.findById(authentication.getEmail())).thenReturn(Optional.empty());
        when(authenticationRepository.save(any(Authentication.class))).thenReturn(authentication);

        // Act
        Authentication registeredUser = authenticationService.register(authentication);

        // Assert
        assertNotNull(registeredUser);
        assertEquals(authentication.getEmail(), registeredUser.getEmail());
        // Ensure that the password is encrypted
        assertNotEquals("password", registeredUser.getPassword());
    }

    @Test
    void testRegister_Failure_EmailExists() {
        // Arrange
        Authentication authentication = new Authentication();
        authentication.setEmail("test@example.com");
        authentication.setPassword("password");

        when(authenticationRepository.findById(authentication.getEmail())).thenReturn(Optional.of(authentication));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> authenticationService.register(authentication));
    }

    @Test
    void testLogin_Success() {
        // Arrange
        Authentication existingUser = new Authentication();
        existingUser.setEmail("test@example.com");
        existingUser.setPassword(new BCryptPasswordEncoder().encode("password"));

        Authentication authentication = new Authentication();
        authentication.setEmail("test@example.com");
        authentication.setPassword("password");

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", "sample_token");

        when(authenticationRepository.findById(authentication.getEmail())).thenReturn(Optional.of(existingUser));
        when(tokenGenerator.generateToken(existingUser)).thenReturn(tokenMap);

        // Act
        Map<String, String> loginResponse = authenticationService.login(authentication);

        // Assert
        assertNotNull(loginResponse);
        assertTrue(loginResponse.containsKey("token"));
        assertEquals("sample_token", loginResponse.get("token"));
    }

    @Test
    void testLogin_Failure_InvalidPassword() {
        // Arrange
        Authentication existingUser = new Authentication();
        existingUser.setEmail("test@example.com");
        existingUser.setPassword(new BCryptPasswordEncoder().encode("password"));

        Authentication authentication = new Authentication();
        authentication.setEmail("test@example.com");
        authentication.setPassword("wrong_password");

        when(authenticationRepository.findById(authentication.getEmail())).thenReturn(Optional.of(existingUser));

        // Act
        Map<String, String> loginResponse = authenticationService.login(authentication);

        // Assert
        assertNotNull(loginResponse);
        assertTrue(loginResponse.containsKey("message"));
        assertEquals("Invalid Password", loginResponse.get("message"));
    }


}

