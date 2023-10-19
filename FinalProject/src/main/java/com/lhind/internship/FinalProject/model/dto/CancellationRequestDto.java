package com.lhind.internship.FinalProject.model.dto;

import com.lhind.internship.FinalProject.model.enums.RequestStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancellationRequestDto {
    private Long id;
    @NotNull(message = "Booking ID cannot be null")
    private Long flightBookingId;

    private RequestStatus status;

    @NotNull(message = "Request date cannot be null")
    private Date requestDate;

    @Size(min = 1, max = 500, message = "Reason must be between 1 and 500 characters")
    private String reason;

    @Size(max = 500, message = "Admin response must be under 500 characters")
    private String adminResponse;
}
