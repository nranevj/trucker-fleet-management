package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll();
    boolean isVehicleRegistered(String vin);
    void addorUpdate(Vehicle vehicle);
    void addorUpdateinBulk(List<Vehicle> vehicleList);
}
