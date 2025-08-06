package com.fleetmanagement.dto;

import java.time.LocalDate;

import com.fleetmanagement.utils.VehicleStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO representing detailed vehicle information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {

	@Schema(description = "Unique identifier of the vehicle", example = "101")
	private Long id;

	@Schema(description = "License plate number of the vehicle", example = "XYZ-1234")
	private String licensePlate;

	@Schema(description = "Model name or type of the vehicle", example = "Toyota Camry")
	private String model;

	@Schema(description = "Current status of the vehicle", example = "ACTIVE")
	private VehicleStatus status;

	@Schema(description = "Date of the last maintenance performed", example = "2025-07-15")
	private LocalDate lastMaintenanceDate;
}
