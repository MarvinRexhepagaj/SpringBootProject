package com.lhind.internship.FinalProject.ServiceTest;


import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.exception.DuplicateException;
import com.lhind.internship.FinalProject.mapper.UserDataMapper;
import com.lhind.internship.FinalProject.mapper.UserMapper;
import com.lhind.internship.FinalProject.model.dto.PasswordDto;
import com.lhind.internship.FinalProject.model.dto.UserDto;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import com.lhind.internship.FinalProject.model.entity.User;
import com.lhind.internship.FinalProject.model.enums.UserRole;
import com.lhind.internship.FinalProject.repository.BookingRepository;
import com.lhind.internship.FinalProject.repository.FlightBookingRepository;
import com.lhind.internship.FinalProject.repository.UserRepository;
import com.lhind.internship.FinalProject.service.UserService;
import com.lhind.internship.FinalProject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightBookingRepository flightBookingRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDataMapper userDataMapper;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, bookingRepository, flightBookingRepository, passwordEncoder, userMapper, userDataMapper);
    }

    @Test
    void getUserById_UserExists_ReturnsUserDto() {
        // Mock the userRepository to return an Optional containing a User object
        final Long userId = 1L;
        final User user = new User();
        user.setId(userId);
        user.setFirstName("Jim");
        user.setLastName("Troy");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Mock the userMapper to map the User object to a UserDto object
        final UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setFirstName("Jim");
        userDto.setLastName("Troy");
        when(userMapper.toDto(user)).thenReturn(userDto);

        // Call the getUserById method
        final UserDto result = userService.getUserById(userId);

        // Assert that the result is not null and is equal to the expected UserDto
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Jim", result.getFirstName());
    }

    @Test
    void getUserById_UserDoesNotExist_ThrowsCustomException() {
        // Mock the userRepository to return an empty Optional
        final Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Call the getUserById method and expect it to throw a CustomException
        assertThrows(CustomException.class, () -> userService.getUserById(userId));
    }


    @Test
    void deleteUserById_UserExists_DeletesUser() {
        // Mock the userRepository to return an Optional containing a User object
        final Long userId = 1L;
        final User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Call the deleteUserById method
        userService.deleteUserById(userId);

        // Verify that the userRepository's deleteById method was called with the correct user id
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteUserById_UserDoesNotExist_ThrowsCustomException() {
        // Mock the userRepository to return an empty Optional
        final Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Call the deleteUserById method and expect it to throw a CustomException
        assertThrows(CustomException.class, () -> userService.deleteUserById(userId));

        // Verify that the userRepository's deleteById method was not called
        verify(userRepository, never()).deleteById(userId);
    }
    @Test
    void registerUser_NewUser_SuccessfullyRegistersUser() {
        // Mock the userRepository's existsByEmail method to return false
        final String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Mock the userDataMapper to return a User entity
        final User user = new User();
        when(userDataMapper.toEntity(any(PasswordDto.class))).thenReturn(user);

        // Mock the passwordEncoder to return the encoded password
        final String rawPassword = "password";
        final String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        // Call the registerUser method
        final PasswordDto userDto = new PasswordDto();
        userDto.setEmail(email);
        userDto.setPassword(rawPassword);
        userService.registerUser(userDto);

        // Verify that the userRepository's save method was called with the correct user entity
        verify(userRepository, times(1)).save(user);

        // Verify that the passwordEncoder's encode method was called with the correct raw password
        verify(passwordEncoder, times(1)).encode(rawPassword);

        // Verify that the user entity has the correct values
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(UserRole.TRAVELLER, user.getRole());
    }

    @Test
    void registerUser_ExistingEmail_ThrowsCustomException() {
        // Mock the userRepository's existsByEmail method to return true
        final String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Call the registerUser method and expect it to throw a CustomException
        final PasswordDto userDto = new PasswordDto();
        userDto.setEmail(email);
        assertThrows(CustomException.class, () -> userService.registerUser(userDto));

        // Verify that the userRepository's save method was not called
        verify(userRepository, never()).save(any(User.class));
    }
}






