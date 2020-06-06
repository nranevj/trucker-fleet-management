package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Vehicle;
import com.project.truckerfleetmanagement.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PutMapping
    public void loadVehicleDetails(@RequestBody List<Vehicle> vehicleList){ vehicleService.addorUpdateinBulk(vehicleList); }

    @GetMapping
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicleList = vehicleService.getAll();
        return vehicleList;
    }
}
