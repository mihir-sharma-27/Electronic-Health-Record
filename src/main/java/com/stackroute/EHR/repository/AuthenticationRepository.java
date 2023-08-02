package com.stackroute.EHR.repository;


import com.stackroute.EHR.model.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//create JPA repository for Authentication
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {
  
}



