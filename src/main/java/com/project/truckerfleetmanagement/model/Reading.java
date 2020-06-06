package com.project.truckerfleetmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    UUID rid;
    String vin;
    double latitude;
    double longitude;
    @JsonFormat(timezone = "UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;
    double fuelVolume;
    double speed;
    boolean engineLightOn;
    boolean engineCoolantLow;
    boolean cruiseControlOn;
    long engineRpm;
    @Embedded
    Tire tires;
}
