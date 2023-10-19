package com.lhind.internship.FinalProject.model.entity;
import com.lhind.internship.FinalProject.model.enums.AirlineCode;
import com.lhind.internship.FinalProject.model.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights",
        uniqueConstraints = @UniqueConstraint(columnNames = {"flight_number", "flight_date"}))
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airline_code", nullable = false)
    @Enumerated(EnumType.STRING)
    private AirlineCode airlineCode;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "flight_date", nullable = false, columnDefinition = "DATE")
    private LocalDate flightDate;

    @Column(name = "departure_time", nullable = false, columnDefinition = "TIME")
    private LocalTime departureTime;

    @Column(name = "aircraft_type")
    private String aircraftType;

    @Column(name = "economy_seats", nullable = false)
    private int economySeats;

    @Column(name = "premium_economy_seats", nullable = false)
    private int premiumEconomySeats;

    @Column(name = "business_seats", nullable = false)
    private int businessSeats;

    @Column(name = "first_seats", nullable = false)
    private int firstSeats;

    @Column(name = "flight_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @Column(name = "price", nullable = false)
    private double price;


    public Flight(Long id) {
        this.id = id;
    }
}