package com.project.truckerfleetmanagement.repository;

import com.project.truckerfleetmanagement.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, String> {

//    @Query("SELECT * FROM alerts")
//    public List<Alert> getAlertsByPriority_HighAnd
}
