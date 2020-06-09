package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.GeoLocation;
import com.project.truckerfleetmanagement.model.Vehicle;
import com.project.truckerfleetmanagement.service.VehicleGeoLocationService;
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
@RequestMapping("vehicle/{VIN}/geolocation/{Xminutes}")
public class VehicleGeoController {
    private VehicleGeoLocationService vehicleGeoLocationService;

    @Autowired
    public VehicleGeoController(VehicleGeoLocationService vehicleGeoLocationService){
        this.vehicleGeoLocationService = vehicleGeoLocationService;
    }

    @Operation(summary = "Find vehicle's geolocation in the last 'X' minutes", tags = {"vehicles"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Geolocation fetched successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GeoLocation.class)))),
            @ApiResponse(responseCode = "422", description = "Vehicle with VIN does not exist / Invalid minutes provided (must be > 0)"),
            @ApiResponse(responseCode = "400", description = "Wrong content type provided for VIN or minutes")
    })
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<GeoLocation> getGeoLocationwithinLastXminutes(@Parameter(description = "Vehicle Identification Number (VIN)") @PathVariable("VIN") String vin, @Parameter(description = "Minutes") @PathVariable("Xminutes") int Xminutes){
        List<GeoLocation> geoLocations = vehicleGeoLocationService.getGeoLocationwithinLastXminutes(vin, Xminutes);
        return geoLocations;
    }
}
