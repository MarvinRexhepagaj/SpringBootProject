package com.lhind.internship.FinalProject.controller;



import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.model.dto.FlightDto;
import com.lhind.internship.FinalProject.model.dto.PasswordDto;
import com.lhind.internship.FinalProject.model.dto.UserDto;
import com.lhind.internship.FinalProject.service.FlightBookingService;
import com.lhind.internship.FinalProject.service.FlightService;
import com.lhind.internship.FinalProject.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final FlightBookingService flightBookingService;

    private final FlightService flightService;

    public UserController(UserService userService, FlightBookingService flightBookingService, FlightService flightService) {
        this.userService = userService;

        this.flightBookingService = flightBookingService;
        this.flightService = flightService;
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)  {

            UserDto userDto = userService.getUserByEmail(email);
            return ResponseEntity.ok(userDto);

    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<List<UserDto>> getUsersByFlightId(@PathVariable Long flightId)  {
        FlightDto flight = flightService.getFlightById(flightId);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }

        List<UserDto> users = userService.getUsersByFlightId(flightId);
        return ResponseEntity.ok(users);
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PasswordDto> createUser(@RequestBody @Valid PasswordDto userDto)  {
        PasswordDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PasswordDto> updateUser(@PathVariable  Long id, @RequestBody @Valid PasswordDto userDto)  {
        PasswordDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)  {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }



}