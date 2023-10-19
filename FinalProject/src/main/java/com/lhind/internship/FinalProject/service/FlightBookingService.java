package com.lhind.internship.FinalProject.service;

import com.lhind.internship.FinalProject.model.dto.FlightBookingDto;

import java.util.List;

public interface FlightBookingService {
    List<FlightBookingDto> getFlightBookingsByFlightId(Long flightId);
}
