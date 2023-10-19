package com.lhind.internship.FinalProject.model.dto;

import com.lhind.internship.FinalProject.model.enums.AirlineCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchCriteria {
    private String origin;
    private String destination;
    private LocalDate flightDate;
    private AirlineCode airlineCode;

}