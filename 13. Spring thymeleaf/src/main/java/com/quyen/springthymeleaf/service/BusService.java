package com.quyen.springthymeleaf.service;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.repository.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusService {
    private final BusRepository busRepository;

    public List<Bus> getAllBus() {
        return busRepository.getAllBus();
    }

    public void create(Bus bus) {
        busRepository.createBus(bus);
    }

    public Bus getBusById(Integer id) {
        return busRepository.getBusById(id);
    }

    public void updateBus(Bus bus) {
        busRepository.updateBus(bus);
    }

    public void deleteBusById(Integer id) {
        busRepository.deleteBusById(id);
    }
}
