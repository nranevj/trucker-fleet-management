package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.service.AlertService;
import com.project.truckerfleetmanagement.service.ReadingService;
import com.project.truckerfleetmanagement.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Add readings", tags = {"readings"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Readings added successfully"),
            @ApiResponse(responseCode = "207", description = "Some of the readings were not added because the Vehicle Identification Number (VIN) provided in those readings do not exist. Such readings are returned as an array.")
    })
    @PostMapping
    public void addReadings(@Parameter(description = "Readings (object)") @RequestBody Reading reading){
        if(vehicleService.isVehicleRegistered(reading.getVin())){
            readingService.addReading(reading);
            alertService.addAlert(reading);
        }
    }
}
