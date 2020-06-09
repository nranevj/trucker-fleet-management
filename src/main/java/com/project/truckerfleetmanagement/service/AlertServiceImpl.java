package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.*;
import com.project.truckerfleetmanagement.repository.AlertRepository;
import com.project.truckerfleetmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService {

    private static final int LOWER_PSI = 32;
    private static final int UPPER_PSI = 36;

    private AlertRepository alertRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository, VehicleRepository vehicleRepository){
        this.alertRepository =  alertRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void addAlert(Reading reading){
        Optional<Vehicle> vehicle = vehicleRepository.getByVin(reading.getVin());
        if(vehicle.isPresent()){
            Vehicle vehicle1 = vehicle.get();
            Alert alert = new Alert();
            alert.setVehicle(vehicle1);
            alert.setTimeStamp(reading.getTimestamp());
            generateAlert(alert, reading, vehicle1);
            alertRepository.save(alert);
        }
    }

    @Override
    public void generateAlert(Alert alert, Reading reading, Vehicle vehicle){
        PriorityInfo priorityInfo = new PriorityInfo();

        //Rule 1: Engine RPM > ReadLine RPM, Priority: HIGH
        if(reading.getEngineRpm() > vehicle.getRedlineRpm()){
            priorityInfo.setPriority(Priority.HIGH);
            priorityInfo.setPriorityMessage("Alert: Engine RPM is higher than Read line RPM");
        }
        //Rule 2: Fuel Volume < 10% of Max Fuel Volume, Priority: MEDIUM
        else if(reading.getFuelVolume() < 0.1 * vehicle.getMaxFuelVolume()){
            priorityInfo.setPriority(Priority.MEDIUM);
            priorityInfo.setPriorityMessage("Alert: Fuel is very low");
        }
        //Rule 3: Engine Coolant is low or Engine Light is ON, Priority: LOW
        else if(reading.isEngineCoolantLow() || reading.isEngineLightOn()){
            priorityInfo.setPriority(Priority.LOW);
            if(reading.isEngineLightOn()){
                priorityInfo.setPriorityMessage("Alert: Engine light is on");
            }
            else{
                priorityInfo.setPriorityMessage("Alert: Engine Coolant is low");
            }
        }
        //Rule 4: Tire pressure of any tire < 32 (lower) psi or > 36 (upper) psi, Priority: LOW
        else{
            int frontLeftTire = reading.getTires().getFrontLeft();
            int frontRightTire = reading.getTires().getFrontRight();
            int rearLeftTire = reading.getTires().getRearLeft();
            int rearRightTire = reading.getTires().getRearRight();

            if(frontLeftTire < LOWER_PSI || frontLeftTire > UPPER_PSI || frontRightTire < LOWER_PSI || frontRightTire > UPPER_PSI
                    || rearLeftTire < LOWER_PSI || rearLeftTire > UPPER_PSI || rearRightTire < LOWER_PSI || rearRightTire > UPPER_PSI) {
                priorityInfo.setPriority(Priority.LOW);

                if(frontLeftTire < LOWER_PSI || frontLeftTire > UPPER_PSI){
                    priorityInfo.setPriorityMessage("Alert: Front left tire pressure is not in safe PSI range. Safe PSI range:"+LOWER_PSI+"-"+UPPER_PSI);
                }
                else if(frontRightTire < LOWER_PSI || frontRightTire > UPPER_PSI){
                    priorityInfo.setPriorityMessage("Alert: Front right tire pressure is not in safe PSI range. Safe PSI range:"+LOWER_PSI+"-"+UPPER_PSI);
                }
                else if(rearLeftTire < LOWER_PSI || rearLeftTire > UPPER_PSI){
                    priorityInfo.setPriorityMessage("Alert: Rear left tire pressure is not in safe PSI range. Safe PSI range:"+LOWER_PSI+"-"+UPPER_PSI);
                }
                else if(rearRightTire < LOWER_PSI || rearRightTire > UPPER_PSI){
                    priorityInfo.setPriorityMessage("Alert: Rear right tire pressure is not in safe PSI range. Safe PSI range:"+LOWER_PSI+"-"+UPPER_PSI);
                }
            }
            //No problems, Priority: NONE
            else{
                priorityInfo.setPriority(Priority.NONE);
                priorityInfo.setPriorityMessage("");
            }
        }

        alert.setPriority(priorityInfo);

        //Here, based on the alert, you can send SMS or email to the user or target system
    }

    @Override
    public List<Alert> getAll(String vin){
        List<Alert> alerts = alertRepository.findAllByVehicle_Vin(vin).stream().filter(a -> a!=null).collect(Collectors.toList());;
        return alerts;
    }

    @Override
    public List<Alert> getAllinLastXhours(Priority priority, int xhours){
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("UTC"));
        LocalDateTime xhoursBefore = currentTime.plusHours(-xhours);
        List<Alert> alerts = alertRepository.findAllByPriority_PriorityAndTimeStampGreaterThanEqual(priority, xhoursBefore);
        alerts = alerts.stream().sorted(Comparator.comparing(Alert::getTimeStamp).reversed()).collect(Collectors.toList());
        return alerts;
    }
}