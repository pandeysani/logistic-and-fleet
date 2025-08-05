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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    @PreAuthorize("hasRole('FLEET_MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<MaintenanceResponse> create(@RequestBody MaintenanceRequest req) {
        return ResponseEntity.ok(maintenanceService.create(req));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER')")
    public ResponseEntity<List<MaintenanceResponse>> getAll() {
        return ResponseEntity.ok(maintenanceService.getAll());
    }

    @GetMapping("/vehicle/{vehicleId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER')")
    public ResponseEntity<List<MaintenanceResponse>> getByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(maintenanceService.getByVehicle(vehicleId));
    }
}

