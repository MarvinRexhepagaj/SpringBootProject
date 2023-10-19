package com.lhind.internship.FinalProject.model.dto;

import com.lhind.internship.FinalProject.model.enums.BookingClass;
import com.lhind.internship.FinalProject.model.enums.BookingStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingDto {
    private Long id;
    @NotNull(message = "Booking ID cannot be null")
    private Long bookingId;

    @NotNull(message = "Flight ID cannot be null")
    private Long flightId;

    private BookingClass bookingClass;
    private BookingStatus bookingStatus;

    @Min(value = 1, message = "At least one seat must be booked")
    private Integer seatsBooked;

    private String declineReason;

    @NotNull(message = "Booking date cannot be null")
    private Date bookingDate;


}
