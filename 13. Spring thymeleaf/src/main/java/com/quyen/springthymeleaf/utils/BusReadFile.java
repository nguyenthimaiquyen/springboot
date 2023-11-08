package com.quyen.springthymeleaf.utils;

import com.quyen.springthymeleaf.entity.Bus;

import java.util.List;

public interface BusReadFile {

    List<Bus> readFile(String filePath);

}
