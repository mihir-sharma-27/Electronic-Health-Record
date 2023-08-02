package com.stackroute.EHR.repository;


import static org.junit.jupiter.api.Assertions.*;

import com.stackroute.EHR.model.Authentication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class AuthenticationRepositoryTest {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    private Authentication testAuthentication;

    @BeforeEach
    void setUp() {
        // Create a sample Authentication object for testing
        testAuthentication = new Authentication();
        testAuthentication.setEmail("test@example.com");
        testAuthentication.setPassword("password");
    }

    @AfterEach
    void tearDown() {
        // Clean up the test data after each test
        authenticationRepository.deleteAll();
    }

    @Test
    void testSaveAuthentication() {
        // Act
        Authentication savedAuthentication = authenticationRepository.save(testAuthentication);

        // Assert
        assertNotNull(savedAuthentication);
        assertEquals(testAuthentication.getEmail(), savedAuthentication.getEmail());
        assertEquals(testAuthentication.getPassword(), savedAuthentication.getPassword());
    }

    @Test
    void testFindByEmail() {
        // Arrange
        authenticationRepository.save(testAuthentication);

        // Act
        Optional<Authentication> foundAuthentication = authenticationRepository.findById(testAuthentication.getEmail());

        // Assert
        assertTrue(foundAuthentication.isPresent());
        assertEquals(testAuthentication, foundAuthentication.get());
    }

    @Test
    void testDeleteAuthentication() {
        // Arrange
        Authentication savedAuthentication = authenticationRepository.save(testAuthentication);

        // Act
        authenticationRepository.deleteById(savedAuthentication.getEmail());

        // Assert
        assertFalse(authenticationRepository.findById(savedAuthentication.getEmail()).isPresent());
    }
}

