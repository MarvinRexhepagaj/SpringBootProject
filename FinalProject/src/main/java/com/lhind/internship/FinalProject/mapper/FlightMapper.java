package com.lhind.internship.FinalProject.mapper;


import com.lhind.internship.FinalProject.model.dto.FlightDto;
import com.lhind.internship.FinalProject.model.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper extends AbstractMapper<Flight, FlightDto> {

    @Override
    public Flight toEntity(FlightDto dto) {
        if (dto == null) {
            return null;
        }

        Flight flight = new Flight();

        flight.setId(dto.getId());
        flight.setAirlineCode(dto.getAirlineCode());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setOrigin(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setFlightDate(dto.getFlightDate());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setAircraftType(dto.getAircraftType());
        flight.setEconomySeats(dto.getEconomySeats());
        flight.setPremiumEconomySeats(dto.getPremiumEconomySeats());
        flight.setBusinessSeats(dto.getBusinessSeats());
        flight.setFirstSeats(dto.getFirstSeats());
        flight.setFlightStatus(dto.getFlightStatus());
        flight.setPrice(dto.getPrice());

        return flight;
    }

    @Override
    public FlightDto toDto(Flight entity) {
        if (entity == null) {
            return null;
        }

        FlightDto flightDto = new FlightDto();

        flightDto.setId(entity.getId());
        flightDto.setAirlineCode(entity.getAirlineCode());
        flightDto.setFlightNumber(entity.getFlightNumber());
        flightDto.setOrigin(entity.getOrigin());
        flightDto.setDestination(entity.getDestination());
        flightDto.setFlightDate(entity.getFlightDate());
        flightDto.setDepartureTime(entity.getDepartureTime());
        flightDto.setAircraftType(entity.getAircraftType());
        flightDto.setEconomySeats(entity.getEconomySeats());
        flightDto.setPremiumEconomySeats(entity.getPremiumEconomySeats());
        flightDto.setBusinessSeats(entity.getBusinessSeats());
        flightDto.setFirstSeats(entity.getFirstSeats());
        flightDto.setFlightStatus(entity.getFlightStatus());
        flightDto.setPrice(entity.getPrice());

        return flightDto;
    }

    public List<FlightDto> toDtoList(List<Flight> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}