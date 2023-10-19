package com.lhind.internship.FinalProject.service;

import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.model.dto.BookingDto;

import java.util.List;

public interface BookingService {




    List<BookingDto> getBookingsForAuthenticatedTraveler(String userEmail, int page, int pageSize);

    List<BookingDto> getBookingsByUserId(Long userId) throws CustomException;

    BookingDto createBooking(BookingDto bookingDto);

    /* Page<BookingDto> getBookingsByUserId(Long userId, int page, int pageSize);*/
}
