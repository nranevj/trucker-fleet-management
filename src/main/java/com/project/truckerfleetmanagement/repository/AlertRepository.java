package com.project.truckerfleetmanagement.repository;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, String> {

    List<Alert> findAllByVehicle_Vin(String vin);
    List<Alert> findAllByPriority_PriorityAndTimeStampGreaterThanEqual(Priority priority, LocalDateTime time);
}
