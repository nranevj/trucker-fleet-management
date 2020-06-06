package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.exception.NoSuchVehicleException;
import com.project.truckerfleetmanagement.model.Vehicle;
import com.project.truckerfleetmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService{

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAll(){
        List<Vehicle> vehicleList = vehicleRepository.findAll().stream().filter(v -> v!=null).collect(Collectors.toList());
        return vehicleList;
    }

    @Override
    public boolean isVehicleRegistered(String vin){
        Optional<Vehicle> vehicle = vehicleRepository.getByVin(vin);
        if(!vehicle.isPresent()){
            System.out.println("Vehicle with "+vin+" is not a registered vehicle.");
            return false;
        }
        //vehicle.orElseThrow(()-> new NoSuchVehicleException("Vehicle with "+vin+" is not a registered vehicle."));
        return true;
    }

    @Override
    public void addorUpdate(Vehicle vehicle) {
        Optional<Vehicle> vehicle1 = vehicleRepository.getByVin(vehicle.getVin());
        Vehicle vehicle2;

        if(vehicle1.isPresent()){
            vehicle2 = vehicle1.get();
        }
        else{
            vehicle2 = new Vehicle();
            vehicle2.setVin(vehicle.getVin());
        }
        vehicle2.setMake(vehicle.getMake());
        vehicle2.setModel(vehicle.getModel());
        vehicle2.setYear(vehicle.getYear());
        vehicle2.setRedlineRpm(vehicle.getRedlineRpm());
        vehicle2.setMaxFuelVolume(vehicle.getMaxFuelVolume());
        vehicle2.setLastServiceDate(vehicle.getLastServiceDate());
        vehicleRepository.save(vehicle2);
    }

    @Override
    public void addorUpdateinBulk(List<Vehicle> vehicleList){
        for(Vehicle vehicle: vehicleList){
            addorUpdate(vehicle);
        }
    }

    @Override
    public void deleteByvin(String vin){
        Optional<Vehicle> vehicle1 = vehicleRepository.getByVin(vin);
        if(vehicle1.isPresent()) {
            vehicleRepository.delete(vehicle1.get());
        }
    }
}