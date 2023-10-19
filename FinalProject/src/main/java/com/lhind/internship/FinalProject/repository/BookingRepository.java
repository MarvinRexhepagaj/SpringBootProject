package com.lhind.internship.FinalProject.repository;

import com.lhind.internship.FinalProject.model.dto.BookingDto;
import com.lhind.internship.FinalProject.model.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Page<Booking> findByUserEmail(String userEmail, Pageable pageable);
    List<Booking> findByUserId(Long userId);

    Optional<Booking> findByIdAndUserEmail(Long bookingId, String userEmail);


}
