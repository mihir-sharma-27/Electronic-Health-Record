package com.stackroute.EHR.model;

import lombok.*;
import javax.persistence.*;

import java.util.List;


@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class PatientHealthDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private  String patientName;
    private double patientHeight;
    private double patientWeight;
    private String patientBloodGroup;
     @ElementCollection
     @CollectionTable(name = "patient_allergies", joinColumns = @JoinColumn(name = "patient_id"))
     @Column(name = "allergy")
     private List<String> patientAllergies;

     @ElementCollection
     @CollectionTable(name = "patient_conditions", joinColumns = @JoinColumn(name = "patient_id"))
     @Column(name = "condition")
     private List<String> patientHealthConditions;
//    private String[] patientAllergies;
//    private String[] patientHealthConditions;

    @ElementCollection
    @CollectionTable(name = "patient_medications", joinColumns = @JoinColumn(name = "patient_id"))
    private List<Medication> patientMedications;
}
