package com.project.truckerfleetmanagement.service;

import com.project.truckerfleetmanagement.model.GeoLocation;

import java.util.List;

public interface VehicleGeoLocationService  {
    public List<GeoLocation> getGeoLocationwithinLastXminutes(String vin, int xMinutes);
}
