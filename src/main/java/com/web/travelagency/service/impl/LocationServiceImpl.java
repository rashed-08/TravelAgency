package com.web.travelagency.service.impl;

import com.web.travelagency.model.Location;
import com.web.travelagency.repository.LocationRepository;
import com.web.travelagency.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocation() {
        List<Location> getAllLocation = locationRepository.findAll();
        if (!getAllLocation.isEmpty()) {
            return locationRepository.findAll();
        }
        return null;
    }
}
