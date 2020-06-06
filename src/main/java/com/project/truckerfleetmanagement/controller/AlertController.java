package com.project.truckerfleetmanagement.controller;

import com.project.truckerfleetmanagement.model.Alert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("alerts")
public class AlertController {

    @GetMapping
    public List<Alert> getAllAlerts(String vin){

        return null;
    }
}
