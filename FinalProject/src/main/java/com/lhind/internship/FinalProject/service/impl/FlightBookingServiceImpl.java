package com.lhind.internship.FinalProject.service.impl;

import com.lhind.internship.FinalProject.mapper.FlightBookingMapper;
import com.lhind.internship.FinalProject.model.dto.FlightBookingDto;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import com.lhind.internship.FinalProject.repository.FlightBookingRepository;
import com.lhind.internship.FinalProject.service.FlightBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightBookingMapper flightBookingMapper;

    public FlightBookingServiceImpl(FlightBookingRepository flightBookingRepository, FlightBookingMapper flightBookingMapper) {
        this.flightBookingRepository = flightBookingRepository;
        this.flightBookingMapper = flightBookingMapper;
    }
@Override
    public List<FlightBookingDto> getFlightBookingsByFlightId(Long flightId) {
        List<FlightBooking> flightBookings = flightBookingRepository.findByFlightId(flightId);
        return flightBookings.stream()
                .map(flightBookingMapper::toDto)
                .collect(Collectors.toList());
    }
}
