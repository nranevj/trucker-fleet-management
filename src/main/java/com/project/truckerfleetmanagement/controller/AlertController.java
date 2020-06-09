package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.Priority;
import com.project.truckerfleetmanagement.service.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("alerts")
@Tag(name = "alert", description = "Alerts API")
public class AlertController {

    private AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService){
        this.alertService = alertService;
    }

    @Operation(summary = "Find all alerts of a vehicle", tags = {"alerts"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Alerts fetched successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alert.class)))),
            @ApiResponse(responseCode = "404", description = "Vehicle Identification Number (VIN) does not exist")
    })
    @GetMapping(path = "{VIN}", produces = {"application/json", "application/xml"})
    public List<Alert> getAllAlerts(@Parameter(description = "Vehicle Identification Number (VIN)") @PathVariable("VIN") String vin){
        List<Alert> alerts = alertService.getAllAlerts(vin);
        return alerts;
    }

    @Operation(summary = "Find all 'a' priority level alerts in last 'x' hours", tags = {"alerts"})
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Alerts fetched successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alert.class)))),
            @ApiResponse(responseCode = "422", description = "Invalid alert priority level / Invalid hours provided (must be > 0)"),
            @ApiResponse(responseCode = "400", description = "Wrong content type provided for priority level or hours")
    })
    @GetMapping(path = "{alertLevel}/{Xhours}", produces = {"application/json", "application/xml"})
    public List<Alert> getAllAlertsinLastXhours(@Parameter(description = "Alert priority level") @PathVariable("alertLevel") Priority priority, @Parameter(description = "Hours", example = "2") @PathVariable("Xhours") int xhours){
       List<Alert> alerts = alertService.getAllAlertsinLastXhours(priority, xhours);
       return alerts;
    }
}
