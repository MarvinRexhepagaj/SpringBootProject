package com.lhind.internship.FinalProject.service.impl;


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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    private final FlightBookingRepository flightBookingRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    private final UserMapper userMapper;
    private final UserDataMapper userDataMapper;


    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, FlightBookingRepository flightBookingRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper, UserDataMapper userDataMapper) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.flightBookingRepository = flightBookingRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userDataMapper = userDataMapper;
    }



    @Override
    public PasswordDto createUser(PasswordDto userDto) {
        // Check if the email already exists in the database
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        User user = userDataMapper.toEntity(userDto);
        User createdUser = userRepository.save(user);
        return userDataMapper.toDto(createdUser);
    }


    @Override
    public List<UserDto> getAllUsers()  {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new CustomException("No users found");
        }

        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Getting user by ID: {}", id);

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            log.error("User not found with ID: {}", id);
            throw new CustomException("User not found with ID: " + id);
        }

        UserDto userDto = userMapper.toDto(user.get());

        log.info("Retrieved user: {}", userDto);

        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new CustomException("User not found with email: " + email));
        return userMapper.toDto(user);
}
    @Override
    public List<UserDto> getUsersByFlightId(Long flightId) {
        log.info("Getting users for flight with ID: {}", flightId);

        List<FlightBooking> flightBookings = flightBookingRepository.findByFlightId(flightId);

        if (flightBookings.isEmpty()) {
            log.error("No flight bookings found for flightId: {}", flightId);
            throw new CustomException("No flight bookings found for flightId: " + flightId);
        }

        List<User> users = flightBookings.stream()
                .map(fb -> fb.getBooking().getUser())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        log.info("Retrieved {} users for flight with ID {}", users.size(), flightId);

        return userMapper.toDtoList(users);
    }



    @Override
    public PasswordDto updateUser(Long id, PasswordDto userDto) {
        log.info("Updating user with ID: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with ID: " + id));

        // Check if the email is the same as other existing users' emails
        if (!existingUser.getEmail().equals(userDto.getEmail()) && userRepository.existsByEmail(userDto.getEmail())) {
            log.error("Email {} already exists", userDto.getEmail());
            throw new CustomException("Email already exists");
        }

        User updatedUser = userDataMapper.toEntity(userDto);
        updatedUser.setId(existingUser.getId()); // Ensure the id is not changed

        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        updatedUser.setPassword(encodedPassword);

        userRepository.save(updatedUser);

        log.info("User with ID {} has been updated", id);

        return userDataMapper.toDto(updatedUser);
    }


    @Override
    public void deleteUserById(Long id) {
        // Check if the user exists
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found with ID: " + id));

        userRepository.deleteById(id);

        log.info("User with ID {} has been deleted", id);
    }
    @Override
    public void registerUser(PasswordDto userDto) {
        // Check if the email is already registered
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomException("Email is already registered.");
        }

        // Create a new user entity
        User user = userDataMapper.toEntity(userDto);

        // Set the encoded password
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);

        // Set the role as TRAVELLER
        user.setRole(UserRole.TRAVELLER);

        // Save the user to the database
        userRepository.save(user);

        log.info("User with email {} has been registered", userDto.getEmail());
    }
}
