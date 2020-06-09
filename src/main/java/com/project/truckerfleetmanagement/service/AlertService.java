package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.Priority;
import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.model.Vehicle;

import java.util.List;

public interface AlertService{
    void addAlert(Reading reading);
    void generateAlert(Alert alert, Reading reading, Vehicle vehicle);
    List<Alert> getAll(String vin);
    List<Alert> getAllinLastXhours(Priority priority, int xhours);
}
