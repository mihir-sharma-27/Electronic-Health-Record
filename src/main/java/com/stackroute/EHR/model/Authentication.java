package com.stackroute.EHR.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {
    @Id
    private String email;
    private String password;
    

    
}
