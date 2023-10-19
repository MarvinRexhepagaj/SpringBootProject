package com.lhind.internship.FinalProject.repository;

import com.lhind.internship.FinalProject.model.dto.FlightDto;
import com.lhind.internship.FinalProject.model.entity.Flight;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import com.lhind.internship.FinalProject.model.enums.AirlineCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {



    @Query("SELECT f FROM Flight f WHERE " +
            "(:origin IS NULL OR f.origin = :origin) AND " +
            "(:destination IS NULL OR f.destination = :destination) AND " +
            "(:flightDate IS NULL OR f.flightDate = :flightDate) AND " +
            "(:airlineCode IS NULL OR f.airlineCode = :airlineCode)")
    List<Flight> searchFlights(@Param("origin") String origin,
                               @Param("destination") String destination,
                               @Param("flightDate") LocalDate flightDate,
                               @Param("airlineCode") AirlineCode airlineCode);

    @Query("SELECT fb.flight FROM FlightBooking fb WHERE fb.booking.id = :bookingId AND fb.booking.user.email = :email")
    List<Flight> findByBookingIdAndEmail(@Param("bookingId") Long bookingId, @Param("email") String email);

    Flight findByFlightNumberAndFlightDate(String flightNumber, LocalDate flightDate);
}
