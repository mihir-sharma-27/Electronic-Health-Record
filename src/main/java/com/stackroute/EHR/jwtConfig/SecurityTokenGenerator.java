package com.stackroute.EHR.jwtConfig;

import com.stackroute.EHR.model.Authentication;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> generateToken(Authentication authentication);
}
