package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.service.AlertService;
import com.project.truckerfleetmanagement.service.ReadingService;
import com.project.truckerfleetmanagement.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping
    public void getReadings(@RequestBody Reading reading){
        if(vehicleService.isVehicleRegistered(reading.getVin())){
            readingService.addReading(reading);
            alertService.addAlert(reading);
        }
    }
}
