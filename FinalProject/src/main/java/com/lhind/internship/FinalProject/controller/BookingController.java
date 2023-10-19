package com.lhind.internship.FinalProject.controller;

import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.mapper.BookingMapper;
import com.lhind.internship.FinalProject.mapper.FlightBookingMapper;
import com.lhind.internship.FinalProject.model.dto.BookingDto;
import com.lhind.internship.FinalProject.model.dto.FlightBookingDto;
import com.lhind.internship.FinalProject.model.dto.UserDto;
import com.lhind.internship.FinalProject.model.entity.Booking;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import com.lhind.internship.FinalProject.model.enums.BookingStatus;
import com.lhind.internship.FinalProject.service.BookingService;
import com.lhind.internship.FinalProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final UserService userService;

    private final BookingService bookingService;

    private final BookingMapper bookingMapper;

    private final FlightBookingMapper flightBookingMapper;


    public BookingController(UserService userService, BookingService bookingService, BookingMapper bookingMapper, FlightBookingMapper flightBookingMapper) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.flightBookingMapper = flightBookingMapper;
    }


    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getBookingsByUserId(@PathVariable Long userId)throws CustomException {
        List<BookingDto> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @GetMapping("/traveler")
    public ResponseEntity<List<BookingDto>> getBookingsForAuthenticatedTraveler(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        List<BookingDto> bookings = bookingService.getBookingsForAuthenticatedTraveler(userEmail, page, pageSize);
        return ResponseEntity.ok(bookings);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody  BookingDto bookingDto) {
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }
}
