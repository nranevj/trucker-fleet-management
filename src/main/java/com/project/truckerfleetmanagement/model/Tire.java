package com.project.truckerfleetmanagement.model;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Tire {
    int frontLeft;
    int frontRight;
    int rearLeft;
    int rearRight;
}
