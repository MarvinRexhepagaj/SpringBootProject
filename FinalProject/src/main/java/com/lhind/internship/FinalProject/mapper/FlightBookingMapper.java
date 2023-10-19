package com.lhind.internship.FinalProject.mapper;

import com.lhind.internship.FinalProject.model.dto.FlightBookingDto;
import com.lhind.internship.FinalProject.model.entity.Booking;
import com.lhind.internship.FinalProject.model.entity.Flight;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightBookingMapper extends AbstractMapper<FlightBooking, FlightBookingDto> {

    @Override
    public FlightBooking toEntity(FlightBookingDto dto) {
        if (dto == null) {
            return null;
        }

        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setId(dto.getId());
        flightBooking.setBooking(new Booking(dto.getBookingId()));
        flightBooking.setFlight(new Flight(dto.getFlightId()));
        flightBooking.setBookingClass(dto.getBookingClass());
        flightBooking.setSeatsBooked(dto.getSeatsBooked());
        flightBooking.setBookingStatus(dto.getBookingStatus());
        flightBooking.setDeclineReason(dto.getDeclineReason());


        return flightBooking;
    }

    @Override
    public FlightBookingDto toDto(FlightBooking entity) {
        if (entity == null) {
            return null;
        }

        FlightBookingDto flightBookingDto = new FlightBookingDto();
        flightBookingDto.setId(entity.getId());
        flightBookingDto.setBookingId(entity.getBooking().getId());
        flightBookingDto.setFlightId(entity.getFlight().getId());
        flightBookingDto.setBookingClass(entity.getBookingClass());
        flightBookingDto.setSeatsBooked(entity.getSeatsBooked());
        flightBookingDto.setBookingStatus(entity.getBookingStatus());
        flightBookingDto.setDeclineReason(entity.getDeclineReason());


        return flightBookingDto;
    }

    public List<FlightBooking> toEntityList(List<FlightBookingDto> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}