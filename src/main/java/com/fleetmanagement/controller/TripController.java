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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    @PreAuthorize("hasRole('FLEET_MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest req) {
        return ResponseEntity.ok(tripService.create(req));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FLEET_MANAGER')")
    public ResponseEntity<List<TripResponse>> getAll() {
        return ResponseEntity.ok(tripService.getAll());
    }

    @GetMapping("/driver/{id}")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<List<TripResponse>> getByDriver(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getByDriverId(id));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('FLEET_MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<TripResponse> updateStatus(@PathVariable Long id, @RequestParam TripStatus status) {
        return ResponseEntity.ok(tripService.updateStatus(id, status));
    }
}

