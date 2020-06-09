package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.Priority;
import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.model.Vehicle;

import java.util.List;

public interface AlertService{
    public void addAlert(Reading reading);
    public void generateAlert(Alert alert, Reading reading, Vehicle vehicle);
    public List<Alert> getAllAlerts(String vin);
    public List<Alert> getAllAlertsinLastXhours(Priority priority, int xhours);
}
