package com.lhind.internship.FinalProject.service;

import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.model.dto.PasswordDto;
import com.lhind.internship.FinalProject.model.dto.UserDto;
import jakarta.validation.constraints.Email;

import java.util.List;

public interface UserService {



    PasswordDto createUser(PasswordDto userDto);

    List<UserDto> getAllUsers() ;


    UserDto getUserById(Long id) ;

    UserDto getUserByEmail(String email) ;

    List<UserDto> getUsersByFlightId(Long flightId);








    PasswordDto updateUser(Long id, PasswordDto userDto);

    void deleteUserById(Long id);

    void registerUser(PasswordDto userDto);
}
