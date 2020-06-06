package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl implements ReadingService{

    private ReadingRepository readingRepository;
    private VehicleService vehicleService;

    @Autowired
    public ReadingServiceImpl(ReadingRepository readingRepository, VehicleService vehicleService){
        this.readingRepository = readingRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public void addReading(Reading reading){
        System.out.println(reading.getVin());
        readingRepository.save(reading);
    }
}
