package com.quyen.springthymeleaf.repository;

import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.utils.BusReadFile;
import com.quyen.springthymeleaf.utils.DriverReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverRepository {

    private List<Driver> driverList;
    private DriverReadFile driverFileReader;
    private static int AUTO_ID = 11;

    @Autowired
    public DriverRepository(@Qualifier("DriverJsonReadFile") DriverReadFile driverFileReader) {
        this.driverFileReader = driverFileReader;
        this.driverList = this.driverFileReader.readFile("classpath:static/drivers.json");
    }

    public List<Driver> getAllDriver() {
        return driverList;
    }

    public void updateDriver(Driver driver) {
        for (int i = 0; i < driverList.size(); i++) {
            if (driverList.get(i).getId() == driver.getId()) {
                driverList.get(i).setName(driver.getName());
                driverList.get(i).setAddress(driver.getAddress());
                driverList.get(i).setPhone(driver.getPhone());
                driverList.get(i).setLevel(driver.getLevel());
                break;
            }
        }
    }

    public Driver getDriverById(Integer id) {
        return driverList.stream().filter(d -> d.getId() == id).findFirst().get();
    }

    public void deleteDriverById(Integer id) {
        for (int i = 0; i < driverList.size(); i++) {
            if (driverList.get(i).getId() == id) {
                driverList.remove(i);
                break;
            }
        }
    }

    public void createDriver(Driver driver) {
        Driver newDriver = Driver.builder()
                .id(AUTO_ID++)
                .name(driver.getName())
                .address(driver.getAddress())
                .phone(driver.getPhone())
                .level(driver.getLevel())
                .build();
        driverList.add(newDriver);
    }
}
