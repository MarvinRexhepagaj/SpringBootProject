package com.lhind.internship.FinalProject.service.impl;

import com.lhind.internship.FinalProject.exception.CustomException;
import com.lhind.internship.FinalProject.mapper.CancellationRequestMapper;
import com.lhind.internship.FinalProject.model.dto.CancellationRequestDto;
import com.lhind.internship.FinalProject.model.entity.CancellationRequest;
import com.lhind.internship.FinalProject.model.entity.FlightBooking;
import com.lhind.internship.FinalProject.model.enums.RequestStatus;
import com.lhind.internship.FinalProject.repository.BookingRepository;
import com.lhind.internship.FinalProject.repository.CancellationRequestRepository;
import com.lhind.internship.FinalProject.repository.FlightBookingRepository;
import com.lhind.internship.FinalProject.service.CancellationRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CancellationRequestServiceImpl implements CancellationRequestService {

    private final CancellationRequestRepository cancellationRequestRepository;
    private final BookingRepository bookingRepository;
    private final FlightBookingRepository flightBookingRepository;

    private final CancellationRequestMapper cancellationRequestMapper;

    public CancellationRequestServiceImpl(CancellationRequestRepository cancellationRequestRepository, BookingRepository bookingRepository, FlightBookingRepository flightBookingRepository, CancellationRequestMapper cancellationRequestMapper) {
        this.cancellationRequestRepository = cancellationRequestRepository;
        this.bookingRepository = bookingRepository;
        this.flightBookingRepository = flightBookingRepository;
        this.cancellationRequestMapper = cancellationRequestMapper;
    }


    @Override
    public void requestCancellation(Long flightBookingId, String reason) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        FlightBooking flightBooking = flightBookingRepository.findById(flightBookingId)
                .orElseThrow(() -> new CustomException("Flight booking not found."));

        String bookingUserEmail = flightBooking.getBooking().getUser().getEmail();
        if (!bookingUserEmail.equals(userEmail)) {
            throw new CustomException("Unauthorized access to cancellation request.");
        }

        // Check if there is already a pending cancellation request for the flight booking
        if (cancellationRequestRepository.existsByFlightBookingIdAndStatus(flightBookingId, RequestStatus.PENDING)) {
            throw new CustomException("There is already a pending cancellation request for this flight booking.");
        }

        CancellationRequestDto cancellationRequestDto = new CancellationRequestDto();
        cancellationRequestDto.setFlightBookingId(flightBookingId);
        cancellationRequestDto.setStatus(RequestStatus.PENDING);
        cancellationRequestDto.setRequestDate(new Date());
        cancellationRequestDto.setReason(reason);

        CancellationRequest cancellationRequest = cancellationRequestMapper.toEntity(cancellationRequestDto);
        cancellationRequest.setFlightBooking(flightBooking);

        cancellationRequestRepository.save(cancellationRequest);
    }

    @Override
    public void approveCancellationRequest(Long cancellationRequestId) {
        CancellationRequest cancellationRequest = cancellationRequestRepository.findById(cancellationRequestId)
                .orElseThrow(() -> new CustomException("Cancellation request not found."));

        cancellationRequest.setStatus(RequestStatus.APPROVED);
        cancellationRequest.setAdminResponse(null); // Set the adminResponse field to null

        // Perform any additional actions related to the approval

        cancellationRequestRepository.save(cancellationRequest);
    }

    @Override
    public void declineCancellationRequest(Long cancellationRequestId, String adminResponse) {
        CancellationRequest cancellationRequest = cancellationRequestRepository.findById(cancellationRequestId)
                .orElseThrow(() -> new CustomException("Cancellation request not found."));

        cancellationRequest.setStatus(RequestStatus.DECLINED);
        cancellationRequest.setAdminResponse(adminResponse);

        // Perform any additional actions related to the decline

        cancellationRequestRepository.save(cancellationRequest);
    }


    // Other methods
}

