package com.fleetmanagement.dto;

import java.time.LocalDate;

import com.fleetmanagement.utils.VehicleStatus;

import lombok.Data;

@Data
public class VehicleRequest {
    private String licensePlate;
    private String model;
    private VehicleStatus status;
    private LocalDate lastMaintenanceDate;
}
