package com.fleetmanagement.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for creating or updating maintenance records.
 */
@Data
public class MaintenanceRequest {

	@NotNull(message = "Vehicle ID is required")
	@Schema(description = "ID of the vehicle to be maintained", example = "101")
	private Long vehicleId;

	@NotBlank(message = "Description is required")
	@Size(max = 255, message = "Description must not exceed 255 characters")
	@Schema(description = "Description of the maintenance task", example = "Oil change and filter replacement")
	private String description;

	@NotNull(message = "Maintenance date is required")
	@PastOrPresent(message = "Maintenance date cannot be in the future")
	@Schema(description = "Date when the maintenance was or will be performed", example = "2025-08-05")
	private LocalDate maintenanceDate;

	@NotNull(message = "Cost is required")
	@DecimalMin(value = "0.0", inclusive = true, message = "Cost must be non-negative")
	@Schema(description = "Cost of the maintenance", example = "250.75")
	private Double cost;
}
