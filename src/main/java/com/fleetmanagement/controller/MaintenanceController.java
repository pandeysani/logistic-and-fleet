package com.fleetmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.dto.MaintenanceRequest;
import com.fleetmanagement.dto.MaintenanceResponse;
import com.fleetmanagement.service.MaintenanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
@Slf4j
public class MaintenanceController {

	private final MaintenanceService maintenanceService;

	@Operation(summary = "Create a maintenance record")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Maintenance created successfully", content = @Content(schema = @Schema(implementation = MaintenanceResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content) })
	@PostMapping
	@PreAuthorize("hasRole('FLEET_MANAGER') or hasRole('ADMIN')")
	public ResponseEntity<MaintenanceResponse> create(@Valid @RequestBody MaintenanceRequest req) {
		log.info("Creating maintenance record for vehicleId: {}", req.getVehicleId());
		MaintenanceResponse response = maintenanceService.create(req);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Get all maintenance records")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of maintenance records", content = @Content(schema = @Schema(implementation = MaintenanceResponse.class))),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content) })
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER')")
	public ResponseEntity<List<MaintenanceResponse>> getAll() {
		log.info("Fetching all maintenance records");
		return ResponseEntity.ok(maintenanceService.getAll());
	}

	@Operation(summary = "Get maintenance records for a specific vehicle")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Maintenance records found", content = @Content(schema = @Schema(implementation = MaintenanceResponse.class))),
			@ApiResponse(responseCode = "404", description = "Vehicle not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access denied", content = @Content) })
	@GetMapping("/vehicle/{vehicleId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER')")
	public ResponseEntity<List<MaintenanceResponse>> getByVehicle(@PathVariable Long vehicleId) {
		log.info("Fetching maintenance records for vehicleId: {}", vehicleId);
		return ResponseEntity.ok(maintenanceService.getByVehicle(vehicleId));
	}
}
