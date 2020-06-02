package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Vehicle;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

    @PutMapping
    public void loadVehicleDetails(@RequestBody List<Vehicle> vehicleList){

        for(Vehicle vehicle: vehicleList){
            System.out.println(vehicle.getLastServiceDate());
        }
    }
}
