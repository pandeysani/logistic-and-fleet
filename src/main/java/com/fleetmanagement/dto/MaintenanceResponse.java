package com.fleetmanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceResponse {
    private Long id;
    private String vehiclePlate;
    private String description;
    private LocalDate maintenanceDate;
    private Double cost;
}

