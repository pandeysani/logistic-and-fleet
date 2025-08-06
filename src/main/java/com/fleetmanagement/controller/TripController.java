package com.fleetmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.dto.TripRequest;
import com.fleetmanagement.dto.TripResponse;
import com.fleetmanagement.service.TripService;
import com.fleetmanagement.utils.TripStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
@Slf4j
public class TripController {

	private final TripService tripService;

	@Operation(summary = "Create a new trip")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Trip created successfully", content = @Content(schema = @Schema(implementation = TripResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content) })
	@PostMapping
	@PreAuthorize("hasRole('FLEET_MANAGER') or hasRole('ADMIN')")
	public ResponseEntity<TripResponse> createTrip(@Valid @RequestBody TripRequest req) {
		log.info("Creating new trip for vehicle: {}", req.getVehicleId());
		return ResponseEntity.ok(tripService.create(req));
	}

	@Operation(summary = "Get all trips")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all trips", content = @Content(schema = @Schema(implementation = TripResponse.class))),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content) })
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER')")
	public ResponseEntity<List<TripResponse>> getAll() {
		log.info("Fetching all trips");
		return ResponseEntity.ok(tripService.getAll());
	}

	@Operation(summary = "Get trips by driver ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Trips for the driver", content = @Content(schema = @Schema(implementation = TripResponse.class))),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Driver not found", content = @Content) })
	@GetMapping("/driver/{id}")
	@PreAuthorize("hasRole('DRIVER')")
	public ResponseEntity<List<TripResponse>> getByDriver(
			@Parameter(description = "ID of the driver", example = "5") @PathVariable Long id) {
		log.info("Fetching trips for driver with ID: {}", id);
		return ResponseEntity.ok(tripService.getByDriverId(id));
	}

	@Operation(summary = "Update trip status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Trip status updated successfully", content = @Content(schema = @Schema(implementation = TripResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid status value", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Trip not found", content = @Content) })
	@PutMapping("/{id}/status")
	@PreAuthorize("hasRole('FLEET_MANAGER') or hasRole('ADMIN')")
	public ResponseEntity<TripResponse> updateStatus(
			@Parameter(description = "Trip ID", example = "101") @PathVariable Long id,

			@Parameter(description = "New status", example = "COMPLETED") @RequestParam TripStatus status) {
		log.info("Updating status of trip {} to {}", id, status);
		return ResponseEntity.ok(tripService.updateStatus(id, status));
	}
}
