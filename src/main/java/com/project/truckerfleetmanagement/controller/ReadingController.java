package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.exception.NoSuchVehicleException;
import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.service.AlertService;
import com.project.truckerfleetmanagement.service.ReadingService;
import com.project.truckerfleetmanagement.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("readings")
public class ReadingController {

    private ReadingService readingService;
    private VehicleService vehicleService;
    private AlertService alertService;

    @Autowired
    public ReadingController(ReadingService readingService, VehicleService vehicleService, AlertService alertService){
        this.readingService = readingService;
        this.vehicleService = vehicleService;
        this.alertService = alertService;
    }

    @Operation(summary = "Add reading of a vehicle", tags = {"readings"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Reading added successfully."),
            @ApiResponse(responseCode = "404", description = "The Vehicle Identification Number (VIN) in the reading is not a registered VIN.")
    })
    @PostMapping
    public void addReading(@Parameter(description = "Readings of a vehicle") @RequestBody Reading reading){
        if(vehicleService.isVehicleRegistered(reading.getVin())){
            readingService.addReading(reading);
            alertService.addAlert(reading);
        }
        else{
            throw new NoSuchVehicleException("Vehicle with VIN:"+reading.getVin()+" is not a registered vehicle.");
        }
    }
}
