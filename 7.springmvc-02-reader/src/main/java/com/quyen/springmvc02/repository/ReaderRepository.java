package com.quyen.springmvc02.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.springmvc02.entity.Reader;
import com.quyen.springmvc02.model.request.ReaderCreationRequest;
import com.quyen.springmvc02.statics.ReaderType;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReaderRepository {

    private static final List<Reader> readers = new ArrayList<>();

    private static int AUTO_ID = 10000;

    private final ObjectMapper objectMapper;

    static {
        for (int i = 0; i < 10; i++) {
            Reader reader = Reader.builder()
                    .id(AUTO_ID++)
                    .fullname("Nguyễn Thị " + i)
                    .address("Hà Nội " + i)
                    .phone("096480628" + i)
                    .readerTypes(Arrays.asList(ReaderType.STUDENT))
                    .build();
            readers.add(reader);
        }
        Reader reader = Reader.builder()
                .id(AUTO_ID++)
                .fullname("Phùng Đức Huy")
                .address("Hà Nội")
                .phone("0777768886")
                .readerTypes(Arrays.asList(ReaderType.POSTGRADUATE, ReaderType.TEACHER))
                .build();
        readers.add(reader);
    }

    public List<Reader> getAll() {
        return readers;
    }

    public void delete(int id) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getId() == id) {
                readers.remove(i);
                return;
            }
        }
    }


    public void createReader(ReaderCreationRequest readerCreationRequest) {
//        Reader reader = objectMapper.convertValue(readerCreationRequest, Reader.class);
//        reader.setId(AUTO_ID++);
        Reader reader = Reader.builder()
                .id(AUTO_ID++)
                .fullname(readerCreationRequest.getFullname())
                .address(readerCreationRequest.getAddress())
                .phone(readerCreationRequest.getPhone())
                .build();

        readers.add(reader);
    }
}
