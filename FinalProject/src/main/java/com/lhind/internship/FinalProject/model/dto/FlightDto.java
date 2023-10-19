package com.lhind.internship.FinalProject.model.dto;

import com.lhind.internship.FinalProject.model.enums.AirlineCode;
import com.lhind.internship.FinalProject.model.enums.FlightStatus;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private Long id;
    private AirlineCode airlineCode;

    @NotBlank(message = "Flight number cannot be blank")
    @Max(value = 999, message = "Flight number cannot exceed 3 digits")
    private String flightNumber;


    @NotBlank(message = "Origin cannot be blank")
    private String origin;

    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @NotNull(message = "Flight date cannot be null")
    private LocalDate flightDate;

    @NotNull(message = "Departure time cannot be null")
    private LocalTime departureTime;

    @NotBlank(message = "Aircraft type cannot be blank")
    private String aircraftType;

    @Min(value = 0, message = "The value must be positive")
    private Integer economySeats;

    @Min(value = 0, message = "The value must be positive")
    private Integer premiumEconomySeats;

    @Min(value = 0, message = "The value must be positive")
    private Integer businessSeats;

    @Min(value = 0, message = "The value must be positive")
    private Integer firstSeats;

    private FlightStatus flightStatus;

    @Min(value = 0, message = "The price must be positive")
    private Double price;

}