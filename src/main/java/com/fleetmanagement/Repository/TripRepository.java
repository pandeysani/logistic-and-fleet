package com.fleetmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fleetmanagement.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDriverId(Long driverId);
}

