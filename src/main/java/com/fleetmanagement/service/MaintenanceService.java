package com.fleetmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fleetmanagement.Repository.MaintenanceRepository;
import com.fleetmanagement.Repository.VehicleRepository;
import com.fleetmanagement.dto.MaintenanceRequest;
import com.fleetmanagement.dto.MaintenanceResponse;
import com.fleetmanagement.entity.Maintenance;
import com.fleetmanagement.entity.Vehicle;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepo;
    private final VehicleRepository vehicleRepo;

    public MaintenanceResponse create(MaintenanceRequest req) {
        Vehicle vehicle = vehicleRepo.findById(req.getVehicleId())
            .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Maintenance maintenance = Maintenance.builder()
            .vehicle(vehicle)
            .description(req.getDescription())
            .maintenanceDate(req.getMaintenanceDate())
            .cost(req.getCost())
            .build();

        maintenance = maintenanceRepo.save(maintenance);
        return mapToResponse(maintenance);
    }

    public List<MaintenanceResponse> getAll() {
        return maintenanceRepo.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    public List<MaintenanceResponse> getByVehicle(Long vehicleId) {
        return maintenanceRepo.findByVehicleId(vehicleId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    private MaintenanceResponse mapToResponse(Maintenance m) {
        return MaintenanceResponse.builder()
            .id(m.getId())
            .vehiclePlate(m.getVehicle().getLicensePlate())
            .description(m.getDescription())
            .maintenanceDate(m.getMaintenanceDate())
            .cost(m.getCost())
            .build();
    }
}

