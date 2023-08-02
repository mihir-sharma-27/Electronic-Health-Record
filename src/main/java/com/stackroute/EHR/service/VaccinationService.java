package com.stackroute.EHR.service;

import com.stackroute.EHR.model.Vaccination;
import com.stackroute.EHR.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationService {
    @Autowired
    private VaccinationRepository vaccinationRepository;

    //method to get all vaccinations
    public List<Vaccination> getAllVaccinations() {
        return vaccinationRepository.findAll();
    }

    //method to get vaccination by id with exception handling
    public Vaccination getVaccineByID(Long id){
        return vaccinationRepository.findById(id).orElseThrow(() -> new RuntimeException("Vaccination not found"));
    }


    //method to save vaccination
    public Vaccination saveVaccination(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    //method to delete vaccination by id
    public void deleteVaccinationById(long id) {
        vaccinationRepository.deleteById(id);
    }

    //method to update vaccination
    public Vaccination updateVaccination(Vaccination vaccination) {
        Vaccination vaccination1=vaccinationRepository.findById(vaccination.getVaccinationId()).orElseThrow(() -> new RuntimeException("Vaccination not found"));
        vaccination1.setVaccinationName(vaccination.getVaccinationName());
        vaccination1.setVaccinationDate(vaccination.getVaccinationDate());
        vaccination1.setNextDueDate(vaccination.getNextDueDate());
        return vaccinationRepository.save(vaccination1);
    }

}
