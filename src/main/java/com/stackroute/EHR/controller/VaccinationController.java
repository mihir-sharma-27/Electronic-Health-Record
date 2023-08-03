package com.stackroute.EHR.controller;

import com.stackroute.EHR.model.Vaccination;
import com.stackroute.EHR.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*") //to enable cross origin requests
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;

    //api endpoint to get all vaccinations which return responseentity
    @GetMapping("/vaccinations")
    public ResponseEntity<List<Vaccination>> getAllVaccinations() {
        return new ResponseEntity<>(vaccinationService.getAllVaccinations(), HttpStatus.OK);
    }

    //api endpoint to get vaccination by id which return responseentity
    @GetMapping("/vaccination/{id}")
    public ResponseEntity<Vaccination> getVaccinationByID(Long id) {
        return new ResponseEntity<>(vaccinationService.getVaccineByID(id), HttpStatus.OK);
    }

    //api endpoint to save vaccination which return responseentity
    @PostMapping("/vaccination")
    public ResponseEntity<Vaccination> saveVaccination(Vaccination vaccination) {
        return new ResponseEntity<>(vaccinationService.saveVaccination(vaccination), HttpStatus.OK);
    }

    //api endpoint to delete vaccination by id which return responseentity
    @DeleteMapping("/vaccination/{id}")
    public ResponseEntity<String> deleteVaccinationById(long id) {
        vaccinationService.deleteVaccinationById(id);
        return new ResponseEntity<>("Vaccination deleted successfully", HttpStatus.OK);
    }

    //api endpoint to update vaccination which return responseentity
    @PutMapping("/vaccination")
    public ResponseEntity<Vaccination> updateVaccination(Vaccination vaccination) {
        return new ResponseEntity<>(vaccinationService.updateVaccination(vaccination), HttpStatus.OK);
    }

}
