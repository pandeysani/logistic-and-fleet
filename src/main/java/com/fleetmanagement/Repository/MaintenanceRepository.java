package com.fleetmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleetmanagement.entity.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByVehicleId(Long vehicleId);
}

