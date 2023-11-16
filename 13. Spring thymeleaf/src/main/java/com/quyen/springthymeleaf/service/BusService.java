package com.quyen.springthymeleaf.service;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.exception.BusNotFoundException;
import com.quyen.springthymeleaf.model.request.BusCreationRequest;
import com.quyen.springthymeleaf.model.request.BusUpdateRequest;
import com.quyen.springthymeleaf.model.response.BusDetailResponse;
import com.quyen.springthymeleaf.repository.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusService {
    public static int AUTO_ID = 11;
    private final BusRepository busRepository;

    public List<Bus> getAll() {
        return busRepository.getAll();
    }

    public List<Bus> create(BusCreationRequest bus) {
        Bus newBus = Bus.builder()
                .id(AUTO_ID++)
                .distance(bus.getDistance())
                .busStop(bus.getBusStop())
                .build();
        return busRepository.create(newBus);
    }

    public BusDetailResponse getById(int id) throws BusNotFoundException {
        Bus bus = busRepository.getById(id);
        return BusDetailResponse.builder()
                .id(bus.getId())
                .distance(bus.getDistance())
                .busStop(bus.getBusStop())
                .build();
    }

    public List<Bus> update(BusUpdateRequest bus) throws BusNotFoundException {
        return busRepository.update(bus);
    }

    public List<Bus> deleteById(int id) throws BusNotFoundException {
        return busRepository.deleteById(id);
    }
}
