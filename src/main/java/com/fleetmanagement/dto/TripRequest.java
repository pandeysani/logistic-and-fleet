package com.fleetmanagement.dto;

import java.time.LocalDateTime;

import com.fleetmanagement.utils.TripStatus;

import lombok.Data;

@Data
public class TripRequest {
    private Long vehicleId;
    private Long driverId;
    private String startLocation;
    private String endLocation;
    private TripStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

