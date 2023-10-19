package com.lhind.internship.FinalProject.service;

import org.springframework.transaction.annotation.Transactional;

public interface CancellationRequestService {
    @Transactional
    void requestCancellation(Long bookingId, String reason);

    void approveCancellationRequest(Long cancellationRequestId);

    void declineCancellationRequest(Long cancellationRequestId, String adminResponse);
}
