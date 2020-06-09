package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl implements ReadingService{

    private ReadingRepository readingRepository;

    @Autowired
    public ReadingServiceImpl(ReadingRepository readingRepository){
        this.readingRepository = readingRepository;
    }

    @Override
    public void addReading(Reading reading){
        readingRepository.save(reading);
    }
}
