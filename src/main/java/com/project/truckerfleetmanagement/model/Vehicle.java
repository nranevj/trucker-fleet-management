package com.project.truckerfleetmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    String vin;
    String make;
    String model;
    int year;
    long redlineRpm;
    double maxFuelVolume;
    @JsonFormat(timezone = "UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime lastServiceDate;
}