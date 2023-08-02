package com.stackroute.EHR.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class Medication {
    private String medicineName;
    private String dosage;
    private String frequency;

}
