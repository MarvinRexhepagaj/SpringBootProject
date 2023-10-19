package com.lhind.internship.FinalProject.repository;

import com.lhind.internship.FinalProject.model.entity.CancellationRequest;
import com.lhind.internship.FinalProject.model.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CancellationRequestRepository extends JpaRepository<CancellationRequest,Long> {




    boolean existsByFlightBookingIdAndStatus(Long flightBookingId, RequestStatus pending);
}
