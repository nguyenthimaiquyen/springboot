package com.quyen.springmvc02.service;

import com.quyen.springmvc02.entity.Reader;
import com.quyen.springmvc02.model.request.ReaderCreationRequest;
import com.quyen.springmvc02.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAll() {
        return readerRepository.getAll();
    }

    public void deleteReader(int id) {
        readerRepository.delete(id);
    }

    public void createReader(ReaderCreationRequest readerCreationRequest) {
        readerRepository.createReader(readerCreationRequest);
    }
}
