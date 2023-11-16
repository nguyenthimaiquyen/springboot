package com.quyen.springthymeleaf.repository;

import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.exception.DriverNotFoundException;
import com.quyen.springthymeleaf.model.request.DriverUpdateRequest;
import com.quyen.springthymeleaf.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DriverRepository {

    private static final String Driver_DATA_FILE_NAME = "drivers";
    private final FileUtil<Driver> fileUtil;


    public List<Driver> getAll() {
        return fileUtil.readDataFromFile(Driver_DATA_FILE_NAME, Driver[].class);
    }

    public List<Driver> update(DriverUpdateRequest driver) throws DriverNotFoundException {
        List<Driver> driverList = getAll();
        if (CollectionUtils.isEmpty(driverList)) {
            throw new DriverNotFoundException("Drivers not found");
        }
        Optional<Driver> driverNeedUpdate = driverList.stream().filter(d -> d.getId() == driver.getId()).findFirst();
        if (driverNeedUpdate.isEmpty()) {
            throw new DriverNotFoundException("Drivers not found");
        }
        for (int i = 0; i < driverList.size(); i++) {
            if (driverList.get(i).getId() == driver.getId()) {
                driverList.get(i).setName(driver.getName());
                driverList.get(i).setAddress(driver.getAddress());
                driverList.get(i).setPhone(driver.getPhone());
                driverList.get(i).setLevel(driver.getLevel());
                fileUtil.writeDataToFile(Driver_DATA_FILE_NAME, driverList);
                return driverList;
            }
        }
        return null;
    }

    public Driver getById(int id) throws DriverNotFoundException {
        List<Driver> driverList = getAll();
        if (CollectionUtils.isEmpty(driverList)) {
            throw new DriverNotFoundException("Drivers not found");
        }
        return driverList.stream().filter(d -> d.getId() == id).findFirst().get();
    }

    public List<Driver> deleteById(int id) throws DriverNotFoundException {
        List<Driver> driverList = getAll();
        if (CollectionUtils.isEmpty(driverList)) {
            throw new DriverNotFoundException("Drivers not found");
        }
        for (int i = 0; i < driverList.size(); i++) {
            if (driverList.get(i).getId() == id) {
                driverList.remove(i);
                fileUtil.writeDataToFile(Driver_DATA_FILE_NAME, driverList);
                return driverList;
            }
        }
        return null;
    }

    public List<Driver> create(Driver driver) {
        List<Driver> driverList = getAll();
        if (CollectionUtils.isEmpty(driverList)) {
            driverList = new ArrayList<>();
        }
        driverList.add(driver);
        fileUtil.writeDataToFile(Driver_DATA_FILE_NAME, driverList);
        return driverList;
    }
}
