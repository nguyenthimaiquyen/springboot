package com.quyen.springthymeleaf.utils;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.entity.Driver;

import java.util.List;

public interface DriverReadFile {

    List<Driver> readFile(String filePath);

}
