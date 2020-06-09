package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll();

    public boolean isVehicleRegistered(String vin);

    public void addorUpdate(Vehicle vehicle);

    public void addorUpdateinBulk(List<Vehicle> vehicleList);
}
