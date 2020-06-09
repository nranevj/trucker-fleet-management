package com.project.truckerfleetmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="alertID", insertable = false, updatable = false, nullable = false)
    @Schema(description = "Unique identifier for alerts")
    UUID alertID;

    @ManyToOne
    @JoinColumn(name = "vin", nullable = false)
    @Schema(description = "Vehicle associated with the alert")
    private Vehicle vehicle;

    @Embedded
    @Schema(description = "Priority details")
    PriorityInfo priority;

    @JsonFormat(timezone = "UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Schema(description = "Date and time the alert was generated")
    private LocalDateTime timeStamp;
}
