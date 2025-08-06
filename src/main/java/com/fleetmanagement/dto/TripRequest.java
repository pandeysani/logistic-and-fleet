package com.fleetmanagement.dto;

import java.time.LocalDateTime;

import com.fleetmanagement.utils.TripStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO for creating or updating a trip.
 */
@Data
public class TripRequest {

	@NotNull(message = "Vehicle ID is required")
	@Schema(description = "ID of the vehicle assigned to the trip", example = "101")
	private Long vehicleId;

	@NotNull(message = "Driver ID is required")
	@Schema(description = "ID of the driver assigned to the trip", example = "55")
	private Long driverId;

	@NotBlank(message = "Start location is required")
	@Schema(description = "Trip start location", example = "New York")
	private String startLocation;

	@NotBlank(message = "End location is required")
	@Schema(description = "Trip destination location", example = "Boston")
	private String endLocation;

	@NotNull(message = "Trip status is required")
	@Schema(description = "Status of the trip", example = "SCHEDULED")
	private TripStatus status;

	@NotNull(message = "Start time is required")
	@Schema(description = "Trip start date and time", example = "2025-08-06T10:00:00")
	private LocalDateTime startTime;

	@Future(message = "End time must be in the future")
	@Schema(description = "Trip end date and time", example = "2025-08-06T14:30:00")
	private LocalDateTime endTime;
}
