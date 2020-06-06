package com.project.truckerfleetmanagement.model;

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
    Priority priority;

    String priorityMessage;
}
