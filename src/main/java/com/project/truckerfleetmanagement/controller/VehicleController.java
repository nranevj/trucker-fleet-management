package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Vehicle;
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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Update vehicles", description = "Add vehicles if doesn't exist", tags = {"vehicles"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Vehicle details added successfully"),
            @ApiResponse(responseCode = "400", description = "Wrong content type for vehicle. Ensure all fields of all vehicles (in input) match their respective type")
    })
    @PutMapping
    public void loadVehicleDetails(@Parameter(description = "List of vehicles to update") @RequestBody List<Vehicle> vehicleList){ vehicleService.addorUpdateinBulk(vehicleList); }

    @Operation(summary = "Find all vehicles", tags = {"vehicles"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Vehicle fetched successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vehicle.class))))
    })
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicleList = vehicleService.getAll();
        return vehicleList;
    }
}
