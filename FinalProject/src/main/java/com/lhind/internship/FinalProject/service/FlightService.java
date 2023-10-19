package com.lhind.internship.FinalProject.service;

import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.model.dto.FlightDto;
import com.lhind.internship.FinalProject.model.dto.FlightSearchCriteria;

import java.util.List;

public interface FlightService {

    List<FlightDto> getAllFlights();

    FlightDto createFlight(FlightDto flightDto);


    FlightDto getFlightById(Long id) ;

    List<FlightDto> searchFlights(FlightSearchCriteria searchCriteria);

    FlightDto updateFlight(Long id, FlightDto flightDto) ;





    void deleteFlight(Long id) throws CustomException;

    List<FlightDto> getFlightsByBookingIdAndEmail(Long bookingId, String email);
}
