package com.lhind.internship.FinalProject.mapper;

import com.lhind.internship.FinalProject.model.dto.CancellationRequestDto;
import com.lhind.internship.FinalProject.model.entity.CancellationRequest;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import org.springframework.stereotype.Component;

@Component
public class CancellationRequestMapper extends AbstractMapper<CancellationRequest, CancellationRequestDto> {

    @Override
    public CancellationRequest toEntity(CancellationRequestDto dto) {
        if (dto == null) {
            return null;
        }

        CancellationRequest cancellationRequest = new CancellationRequest();
        cancellationRequest.setId(dto.getId());

        if (dto.getFlightBookingId() != null) {
            FlightBooking flightBooking = new FlightBooking();
            flightBooking.setId(dto.getFlightBookingId());
            cancellationRequest.setFlightBooking(flightBooking);
        }

        cancellationRequest.setStatus(dto.getStatus());
        cancellationRequest.setRequestDate(dto.getRequestDate());
        cancellationRequest.setReason(dto.getReason());
        cancellationRequest.setAdminResponse(dto.getAdminResponse());

        return cancellationRequest;
    }

    @Override
    public CancellationRequestDto toDto(CancellationRequest entity) {
        if (entity == null) {
            return null;
        }

        CancellationRequestDto cancellationRequestDto = new CancellationRequestDto();
        cancellationRequestDto.setId(entity.getId());

        if (entity.getFlightBooking() != null) {
            cancellationRequestDto.setFlightBookingId(entity.getFlightBooking().getId());
        }

        cancellationRequestDto.setStatus(entity.getStatus());
        cancellationRequestDto.setRequestDate(entity.getRequestDate());
        cancellationRequestDto.setReason(entity.getReason());
        cancellationRequestDto.setAdminResponse(entity.getAdminResponse());

        return cancellationRequestDto;
    }
}
