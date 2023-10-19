package com.lhind.internship.FinalProject.model.dto;

import com.lhind.internship.FinalProject.model.entity.CancellationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingDto {

    private Long id;
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Booking date cannot be null")
    private LocalDateTime bookingDate;


    private List<FlightBookingDto> flightBookings = new ArrayList<>();





}