package com.quyen.phanconglaixe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.phanconglaixe.entity.Driver;
import com.quyen.phanconglaixe.exception.DriverNotFoundException;
import com.quyen.phanconglaixe.model.request.DriverRequest;
import com.quyen.phanconglaixe.model.response.DriverLevelResponse;
import com.quyen.phanconglaixe.model.response.DriverResponse;
import com.quyen.phanconglaixe.repository.DriverRepository;
import com.quyen.phanconglaixe.statics.Level;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final ObjectMapper objectMapper;


    public List<DriverResponse> getAll() {
        List<Driver> drivers = driverRepository.findAll();
        if (!CollectionUtils.isEmpty(drivers)) {
            return drivers.stream().map(
                    driver -> objectMapper.convertValue(driver, DriverResponse.class)
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public DriverResponse getDriverDetails(Long id) throws DriverNotFoundException {
        return driverRepository.findById(id).map(
            driver -> objectMapper.convertValue(driver, DriverResponse.class)
        ).orElseThrow( () -> new DriverNotFoundException("Driver with id " + id + " could not be found!"));
    }

    @Transactional
    public void saveDriver(DriverRequest request) {
        Driver driver = objectMapper.convertValue(request, Driver.class);
        if (!ObjectUtils.isEmpty(driver.getId())) {
            Driver driverNeedUpdate = driverRepository.findById(driver.getId()).get();
            driverNeedUpdate.setName(request.getName());
            driverNeedUpdate.setAddress(request.getAddress());
            driverNeedUpdate.setPhone(request.getPhone());
            driverNeedUpdate.setLevel(request.getLevel());
            driverRepository.save(driverNeedUpdate);
            return;
        }
        driverRepository.save(driver);
    }

    public List<DriverLevelResponse> getDriverLevel() {
        return List.of(
                DriverLevelResponse.builder()
                        .code(Level.A.getCode()).name(Level.A.getName()).build(),
                DriverLevelResponse.builder()
                        .code(Level.B.getCode()).name(Level.B.getName()).build(),
                DriverLevelResponse.builder()
                        .code(Level.C.getCode()).name(Level.C.getName()).build(),
                DriverLevelResponse.builder()
                        .code(Level.D.getCode()).name(Level.D.getName()).build(),
                DriverLevelResponse.builder()
                        .code(Level.E.getCode()).name(Level.E.getName()).build(),
                DriverLevelResponse.builder()
                        .code(Level.F.getCode()).name(Level.F.getName()).build()
        );
    }

    @Transactional
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
