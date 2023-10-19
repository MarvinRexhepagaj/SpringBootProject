package com.lhind.internship.FinalProject.controller;

import com.lhind.internship.FinalProject.model.dto.CancellationRequestDto;
import com.lhind.internship.FinalProject.model.enums.RequestStatus;
import com.lhind.internship.FinalProject.service.BookingService;
import com.lhind.internship.FinalProject.service.CancellationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/cancellation-requests")
public class CancellationRequestController {
    private final BookingService bookingService;
    private final CancellationRequestService cancellationRequestService;

    public CancellationRequestController(BookingService bookingService, CancellationRequestService cancellationRequestService) {
        this.bookingService = bookingService;
        this.cancellationRequestService = cancellationRequestService;
    }
    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @PostMapping("/{bookingId}")
    public ResponseEntity<String> requestCancellation(@PathVariable Long bookingId, @RequestBody CancellationRequestDto cancellationRequestDto) {
        String reason = cancellationRequestDto.getReason();
        cancellationRequestService.requestCancellation(bookingId, reason);
        return ResponseEntity.ok("Cancellation request submitted successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{cancellationRequestId}/approve")
    public ResponseEntity<String> approveCancellationRequest(@PathVariable Long cancellationRequestId) {
        cancellationRequestService.approveCancellationRequest(cancellationRequestId);
        return ResponseEntity.ok("Cancellation request approved successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{cancellationRequestId}/decline")
    public ResponseEntity<String> declineCancellationRequest(@PathVariable Long cancellationRequestId, @RequestBody CancellationRequestDto cancellationRequestDto) {
        String adminResponse = cancellationRequestDto.getAdminResponse();
        cancellationRequestService.declineCancellationRequest(cancellationRequestId, adminResponse);
        return ResponseEntity.ok("Cancellation request declined successfully.");
    }
}
