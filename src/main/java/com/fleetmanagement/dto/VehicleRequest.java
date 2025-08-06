package com.fleetmanagement.dto;

import java.time.LocalDate;

import com.fleetmanagement.utils.VehicleStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

/**
 * DTO for creating or updating vehicle information.
 */
@Data
public class VehicleRequest {

	@NotBlank(message = "License plate is required")
	@Schema(description = "Unique license plate number of the vehicle", example = "XYZ-1234")
	private String licensePlate;

	@NotBlank(message = "Model is required")
	@Schema(description = "Model name or type of the vehicle", example = "Toyota Camry")
	private String model;

	@NotNull(message = "Vehicle status is required")
	@Schema(description = "Current status of the vehicle", example = "ACTIVE")
	private VehicleStatus status;

	@PastOrPresent(message = "Last maintenance date cannot be in the future")
	@Schema(description = "Date of the last maintenance performed on the vehicle", example = "2025-07-15")
	private LocalDate lastMaintenanceDate;
}
