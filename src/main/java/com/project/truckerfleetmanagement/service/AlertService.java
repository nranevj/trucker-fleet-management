package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Alert;
import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.model.Vehicle;

public interface AlertService{
    public void addAlert(Reading reading);
    public void generateAlert(Alert alert, Reading reading, Vehicle vehicle);
}
