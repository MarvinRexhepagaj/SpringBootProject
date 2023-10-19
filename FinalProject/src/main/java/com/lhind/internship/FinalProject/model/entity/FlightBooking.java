package com.lhind.internship.FinalProject.model.entity;


import com.lhind.internship.FinalProject.model.enums.BookingClass;
import com.lhind.internship.FinalProject.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight_booking")
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "booking_class", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingClass bookingClass;

    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    @Column(name = "booking_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "decline_reason")
    private String declineReason;

    @OneToOne(mappedBy = "flightBooking", cascade = CascadeType.ALL, orphanRemoval = true)
    private CancellationRequest cancellationRequest;


}