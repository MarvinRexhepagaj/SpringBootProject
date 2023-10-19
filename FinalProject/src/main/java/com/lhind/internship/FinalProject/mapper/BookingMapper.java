package com.lhind.internship.FinalProject.mapper;

import com.lhind.internship.FinalProject.model.dto.BookingDto;
import com.lhind.internship.FinalProject.model.entity.Booking;
import com.lhind.internship.FinalProject.model.entity.CancellationRequest;
import com.lhind.internship.FinalProject.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper extends AbstractMapper<Booking, BookingDto> {

    @Override
    public Booking toEntity(BookingDto dto) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setId(dto.getId());
            booking.setUser(new User(dto.getUserId()));
        booking.setBookingDate(dto.getBookingDate());
        booking.setFlightBookings(dto.getFlightBookings().stream()
                .map(fbDto -> new FlightBookingMapper().toEntity(fbDto))
                .collect(Collectors.toList()));

        return booking;
    }

    @Override
    public BookingDto toDto(Booking entity) {
        if (entity == null) {
            return null;
        }

        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(entity.getId());
        bookingDto.setUserId(entity.getUser().getId());
        bookingDto.setBookingDate(entity.getBookingDate());
        bookingDto.setFlightBookings(entity.getFlightBookings().stream()
                .map(fb -> new FlightBookingMapper().toDto(fb))
                .collect(Collectors.toList()));
        return bookingDto;
    }

    public List<BookingDto> toDtoList(List<Booking> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}

