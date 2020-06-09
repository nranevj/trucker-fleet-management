package com.project.truckerfleetmanagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class PriorityInfo {

    @Enumerated(EnumType.STRING)
    @Schema(description = "Priority level")
    Priority priority;

    @Schema(description = "Priority message")
    String priorityMessage;
}
