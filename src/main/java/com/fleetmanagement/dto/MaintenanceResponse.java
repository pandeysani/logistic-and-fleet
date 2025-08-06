package com.fleetmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO representing a maintenance record in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaintenanceResponse {

    @Schema(description = "Unique identifier of the maintenance record", example = "501")
    private Long id;

    @Schema(description = "License plate of the vehicle", example = "ABC-1234")
    private String vehiclePlate;

    @Schema(description = "Description of the maintenance task", example = "Engine oil and filter replacement")
    private String description;

    @Schema(description = "Date of the maintenance", example = "2025-08-01")
    private LocalDate maintenanceDate;

    @Schema(description = "Total cost of the maintenance", example = "145.75")
    private Double cost;
}
