package com.project.truckerfleetmanagement.repository;

import com.project.truckerfleetmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    public Optional<Vehicle> getByVin(String vin);
    public List<Vehicle> findAll();
    public void deleteByVin(String vin);
}
