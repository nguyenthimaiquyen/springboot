package com.quyen.test.service;

import com.quyen.test.entity.FileEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileService {
    public FileEntity findByName(String fileName) {
        return null;
    }

    public byte[] download(String fileName) {
        return new byte[0];
    }
}
