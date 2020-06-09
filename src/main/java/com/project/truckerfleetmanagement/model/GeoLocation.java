package com.project.truckerfleetmanagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocation {
    @Schema(description = "Latitude")
    double latitude;
    @Schema(description = "Longitude")
    double longitude;
}