package com.fleetmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fleetmanagement.Repository.VehicleRepository;
import com.fleetmanagement.dto.VehicleRequest;
import com.fleetmanagement.dto.VehicleResponse;
import com.fleetmanagement.entity.Vehicle;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepo;

    public VehicleResponse create(VehicleRequest req) {
        Vehicle vehicle = Vehicle.builder()
            .licensePlate(req.getLicensePlate())
            .model(req.getModel())
            .status(req.getStatus())
            .lastMaintenanceDate(req.getLastMaintenanceDate())
            .build();

        vehicle = vehicleRepo.save(vehicle);
        return mapToResponse(vehicle);
    }

    public List<VehicleResponse> getAll() {
        return vehicleRepo.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public VehicleResponse getById(Long id) {
        Vehicle v = vehicleRepo.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return mapToResponse(v);
    }

    public VehicleResponse update(Long id, VehicleRequest req) {
        Vehicle v = vehicleRepo.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        v.setLicensePlate(req.getLicensePlate());
        v.setModel(req.getModel());
        v.setStatus(req.getStatus());
        v.setLastMaintenanceDate(req.getLastMaintenanceDate());
        return mapToResponse(vehicleRepo.save(v));
    }

    public void delete(Long id) {
        vehicleRepo.deleteById(id);
    }

    private VehicleResponse mapToResponse(Vehicle v) {
        return VehicleResponse.builder()
            .id(v.getId())
            .licensePlate(v.getLicensePlate())
            .model(v.getModel())
            .status(v.getStatus())
            .lastMaintenanceDate(v.getLastMaintenanceDate())
            .build();
    }
}
