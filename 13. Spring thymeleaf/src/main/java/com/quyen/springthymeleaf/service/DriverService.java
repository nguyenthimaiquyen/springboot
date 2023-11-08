package com.quyen.springthymeleaf.service;

import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    public List<Driver> getAllDriver() {
        return driverRepository.getAllDriver();
    }

    public void updateDriver(Driver driver) {
        driverRepository.updateDriver(driver);
    }

    public Driver getDriverById(Integer id) {
        return driverRepository.getDriverById(id);
    }

    public void deleteDriverById(Integer id) {
        driverRepository.deleteDriverById(id);
    }

    public void create(Driver driver) {
        driverRepository.createDriver(driver);
    }
}
