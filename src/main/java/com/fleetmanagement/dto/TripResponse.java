package com.fleetmanagement.dto;

import java.time.LocalDateTime;

import com.fleetmanagement.utils.TripStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {
    private Long id;
    private String vehiclePlate;
    private String driverName;
    private String startLocation;
    private String endLocation;
    private TripStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
