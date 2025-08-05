package com.fleetmanagement.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MaintenanceRequest {
    private Long vehicleId;
    private String description;
    private LocalDate maintenanceDate;
    private Double cost;
}

