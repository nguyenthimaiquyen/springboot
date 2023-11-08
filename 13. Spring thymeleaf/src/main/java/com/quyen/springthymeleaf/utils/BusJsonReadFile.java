package com.quyen.springthymeleaf.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.springthymeleaf.entity.Bus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component("BusJsonReadFile")
public class BusJsonReadFile implements BusReadFile {
    private final ResourceLoader resourceLoader;

    public BusJsonReadFile(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Bus> readFile(String filePath) {
        //tạo list rỗng để chứa dữ liệu
        List<Bus> busList = new ArrayList<>();

        try {
            //Load file
            Resource resource = resourceLoader.getResource(filePath);
            //Đọc dữ liệu từ file
            String data = new String(Files.readAllBytes(resource.getFile().toPath()));
            //sử dụng thư viện Jackson để ánh xạ dữ liệu từ dạng String thành arrayList
            ObjectMapper objectMapper = new ObjectMapper();
            busList = objectMapper.readValue(data, new TypeReference<List<Bus>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return busList;
    }
}
