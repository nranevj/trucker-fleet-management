package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAll();

    public boolean isVehicleRegistered(String vin);

    public void addorUpdate(Vehicle vehicle);

    public void addorUpdateinBulk(List<Vehicle> vehicleList);

    public void deleteByvin(String vin);
}
