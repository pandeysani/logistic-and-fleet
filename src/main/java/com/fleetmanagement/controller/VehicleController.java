package com.fleetmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleetmanagement.dto.VehicleRequest;
import com.fleetmanagement.dto.VehicleResponse;
import com.fleetmanagement.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@Validated
public class VehicleController {

	private final VehicleService vehicleService;

	@Operation(summary = "Create a new vehicle")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Vehicle created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "403", description = "Access denied") })
	@PostMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('FLEET_MANAGER')")
	public ResponseEntity<VehicleResponse> create(@Valid @RequestBody VehicleRequest req) {
		return ResponseEntity.ok(vehicleService.create(req));
	}

	@Operation(summary = "Get list of all vehicles")
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER', 'DRIVER')")
	public ResponseEntity<List<VehicleResponse>> getAll() {
		return ResponseEntity.ok(vehicleService.getAll());
	}

	@Operation(summary = "Get a vehicle by its ID")
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER', 'DRIVER')")
	public ResponseEntity<VehicleResponse> getById(
			@Parameter(description = "ID of the vehicle to retrieve") @PathVariable Long id) {
		return ResponseEntity.ok(vehicleService.getById(id));
	}

	@Operation(summary = "Update a vehicle by its ID")
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('FLEET_MANAGER')")
	public ResponseEntity<VehicleResponse> update(
			@Parameter(description = "ID of the vehicle to update") @PathVariable Long id,
			@Valid @RequestBody VehicleRequest req) {
		return ResponseEntity.ok(vehicleService.update(id, req));
	}

	@Operation(summary = "Delete a vehicle by its ID")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> delete(@Parameter(description = "ID of the vehicle to delete") @PathVariable Long id) {
		vehicleService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
