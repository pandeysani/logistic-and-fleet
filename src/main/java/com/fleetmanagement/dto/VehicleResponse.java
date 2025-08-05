package com.fleetmanagement.dto;

import java.time.LocalDate;

import com.fleetmanagement.entity.VehicleStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String model;
    private VehicleStatus status;
    private LocalDate lastMaintenanceDate;
}
