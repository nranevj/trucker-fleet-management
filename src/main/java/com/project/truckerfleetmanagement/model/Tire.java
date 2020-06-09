package com.project.truckerfleetmanagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Tire {
    @Schema(description = "Front left tire")
    int frontLeft;
    @Schema(description = "Front right tire")
    int frontRight;
    @Schema(description = "Rear left tire")
    int rearLeft;
    @Schema(description = "Rear right tire")
    int rearRight;
}
