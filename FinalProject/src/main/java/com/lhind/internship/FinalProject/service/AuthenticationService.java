package com.lhind.internship.FinalProject.service;

import com.lhind.internship.FinalProject.model.dto.AuthenticationRequest;
import com.lhind.internship.FinalProject.model.dto.AuthenticationResponse;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws AuthenticationException;
}
