package com.quyen.springthymeleaf.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.entity.Driver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component("DriverJsonReadFile")
public class DriverJsonReadFile implements DriverReadFile {
    private final ResourceLoader resourceLoader;

    public DriverJsonReadFile(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Driver> readFile(String filePath) {
        //tạo list rỗng để chứa dữ liệu
        List<Driver> driverList = new ArrayList<>();

        try {
            //Load file
            Resource resource = resourceLoader.getResource(filePath);
            //Đọc dữ liệu từ file
            String data = new String(Files.readAllBytes(resource.getFile().toPath()));
            //sử dụng thư viện Jackson để ánh xạ dữ liệu từ dạng String thành arrayList
            ObjectMapper objectMapper = new ObjectMapper();
            driverList = objectMapper.readValue(data, new TypeReference<List<Driver>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driverList;
    }
}
