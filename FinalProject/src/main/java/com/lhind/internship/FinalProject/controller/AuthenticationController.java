package com.lhind.internship.FinalProject.controller;

import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.model.dto.AuthenticationRequest;
import com.lhind.internship.FinalProject.model.dto.AuthenticationResponse;
import com.lhind.internship.FinalProject.model.dto.PasswordDto;
import com.lhind.internship.FinalProject.model.dto.UserDto;
import com.lhind.internship.FinalProject.service.UserService;
import com.lhind.internship.FinalProject.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST,path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid PasswordDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully.");
    }
}