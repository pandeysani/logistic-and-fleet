package com.fleetmanagement.dto;

import java.time.LocalDateTime;

import com.fleetmanagement.utils.TripStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO representing trip details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

	@Schema(description = "Unique ID of the trip", example = "1001")
	private Long id;

	@Schema(description = "License plate of the assigned vehicle", example = "ABC-1234")
	private String vehiclePlate;

	@Schema(description = "Full name of the assigned driver", example = "John Doe")
	private String driverName;

	@Schema(description = "Starting location of the trip", example = "New York")
	private String startLocation;

	@Schema(description = "Destination of the trip", example = "Boston")
	private String endLocation;

	@Schema(description = "Current status of the trip", example = "IN_PROGRESS")
	private TripStatus status;

	@Schema(description = "Trip start date and time", example = "2025-08-06T08:00:00")
	private LocalDateTime startTime;

	@Schema(description = "Trip end date and time", example = "2025-08-06T12:30:00")
	private LocalDateTime endTime;
}
