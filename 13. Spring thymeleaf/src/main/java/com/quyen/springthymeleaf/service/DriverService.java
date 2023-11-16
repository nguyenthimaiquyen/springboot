package com.quyen.springthymeleaf.service;

import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.exception.DriverNotFoundException;
import com.quyen.springthymeleaf.model.request.DriverCreationRequest;
import com.quyen.springthymeleaf.model.request.DriverUpdateRequest;
import com.quyen.springthymeleaf.model.response.DriverDetailResponse;
import com.quyen.springthymeleaf.repository.DriverRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    public static int AUTO_ID = 11;
    private final DriverRepository driverRepository;


    public List<Driver> getAll() {
        return driverRepository.getAll();
    }

    public List<Driver> update(DriverUpdateRequest driver) throws DriverNotFoundException {
        return driverRepository.update(driver);
    }

    public DriverDetailResponse getById(int id) throws DriverNotFoundException {
        Driver driver = driverRepository.getById(id);
        return DriverDetailResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .address(driver.getAddress())
                .phone(driver.getPhone())
                .level(driver.getLevel())
                .build();
    }

    public List<Driver> deleteById(int id) throws DriverNotFoundException {
        return driverRepository.deleteById(id);
    }

    public List<Driver> create(DriverCreationRequest driver) {
        Driver newDriver = Driver.builder()
                .id(AUTO_ID++)
                .name(driver.getName())
                .address(driver.getAddress())
                .phone(driver.getPhone())
                .level(driver.getLevel())
                .build();
        return driverRepository.create(newDriver);
    }
}
