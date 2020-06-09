package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.GeoLocation;
import com.project.truckerfleetmanagement.model.Reading;
import com.project.truckerfleetmanagement.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleGeoLocatonServiceImpl implements VehicleGeoLocationService{

    private ReadingRepository readingRepository;

    @Autowired
    public VehicleGeoLocatonServiceImpl(ReadingRepository readingRepository){
        this.readingRepository = readingRepository;
    }

    @Override
    public List<GeoLocation> getGeoLocationwithinLastXminutes(String vin, int xMinutes){
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("UTC"));
        LocalDateTime xMinutesBefore = currentTime.plusMinutes(-xMinutes);
        List<Reading> readings = readingRepository.findAllByVinAndTimestampIsGreaterThanEqual(vin, xMinutesBefore);
        List<GeoLocation> geoLocations = readings.stream().sorted(Comparator.comparing(Reading::getTimestamp).reversed()).map(r -> new GeoLocation(r.getLatitude(), r.getLongitude())).collect(Collectors.toList());
        return geoLocations;
    }
}
