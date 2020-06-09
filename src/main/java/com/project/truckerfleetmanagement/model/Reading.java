package com.project.truckerfleetmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "readings")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="rid", insertable = false, updatable = false, nullable = false)
    @Schema(description = "Unique identifier for a reading")
    UUID rid;
    @Schema(description = "Vehicle Identification Number")
    String vin;
    @Schema(description = "Latitude")
    double latitude;
    @Schema(description = "Longitude")
    double longitude;
    @JsonFormat(timezone = "UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Schema(description = "Date and time the reading was generated")
    private LocalDateTime timestamp;
    @Schema(description = "Current fuel level")
    double fuelVolume;
    @Schema(description = "Current speed of the vehicle (miles/hr)")
    double speed;
    @Schema(description = "Indicates whether engine light is on or not")
    boolean engineLightOn;
    @Schema(description = "Indicates whether engine coolant is low or not")
    boolean engineCoolantLow;
    @Schema(description = "Indicates whether cruise control is on or not")
    boolean cruiseControlOn;
    @Schema(description = "Current revolutions per minute (RPM) of the vehicle")
    long engineRpm;
    @Embedded
    @Schema(description = "Tire details")
    Tire tires;
}
