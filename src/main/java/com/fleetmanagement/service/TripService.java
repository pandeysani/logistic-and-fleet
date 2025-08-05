package com.fleetmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fleetmanagement.Repository.TripRepository;
import com.fleetmanagement.Repository.UserRepository;
import com.fleetmanagement.Repository.VehicleRepository;
import com.fleetmanagement.dto.TripRequest;
import com.fleetmanagement.dto.TripResponse;
import com.fleetmanagement.entity.Trip;
import com.fleetmanagement.entity.TripStatus;
import com.fleetmanagement.entity.User;
import com.fleetmanagement.entity.Vehicle;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepo;
    private final VehicleRepository vehicleRepo;
    private final UserRepository userRepo;

    public TripResponse create(TripRequest req) {
        Vehicle vehicle = vehicleRepo.findById(req.getVehicleId())
            .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        User driver = userRepo.findById(req.getDriverId())
            .orElseThrow(() -> new RuntimeException("Driver not found"));

        Trip trip = Trip.builder()
            .vehicle(vehicle)
            .driver(driver)
            .startLocation(req.getStartLocation())
            .endLocation(req.getEndLocation())
            .status(req.getStatus())
            .startTime(req.getStartTime())
            .endTime(req.getEndTime())
            .build();

        trip = tripRepo.save(trip);
        return mapToResponse(trip);
    }

    public List<TripResponse> getAll() {
        return tripRepo.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public List<TripResponse> getByDriverId(Long driverId) {
        return tripRepo.findByDriverId(driverId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public TripResponse updateStatus(Long id, TripStatus status) {
        Trip trip = tripRepo.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setStatus(status);
        return mapToResponse(tripRepo.save(trip));
    }

    private TripResponse mapToResponse(Trip trip) {
        return TripResponse.builder()
            .id(trip.getId())
            .vehiclePlate(trip.getVehicle().getLicensePlate())
            .driverName(trip.getDriver().getName())
            .startLocation(trip.getStartLocation())
            .endLocation(trip.getEndLocation())
            .status(trip.getStatus())
            .startTime(trip.getStartTime())
            .endTime(trip.getEndTime())
            .build();
    }
}

