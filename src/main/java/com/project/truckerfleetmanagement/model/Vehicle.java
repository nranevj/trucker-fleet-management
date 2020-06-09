package com.project.truckerfleetmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @Schema(description = "Vehicle Identification Number")
    String vin;
    @Schema(description = "Vehicle manufacturer")
    String make;
    @Schema(description = "Vehicle model")
    String model;
    @Schema(description = "Vehicle model year")
    int year;
    @Schema(description = "Maximum speed (in RPM) the vehicle can operate")
    long redlineRpm;
    @Schema(description = "Maximum fuel tank capacity (in litres)")
    double maxFuelVolume;
    @JsonFormat(timezone = "UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Schema(description = "Latest date the vehicle was serviced")
    private LocalDateTime lastServiceDate;
}