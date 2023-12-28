package com.quyen.phanconglaixe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.phanconglaixe.entity.Bus;
import com.quyen.phanconglaixe.exception.BusNotFoundException;
import com.quyen.phanconglaixe.model.request.BusRequest;
import com.quyen.phanconglaixe.model.response.BusResponse;
import com.quyen.phanconglaixe.repository.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BusService {
    private final BusRepository busRepository;
    private final ObjectMapper objectMapper;

    public List<BusResponse> getAll() {
        List<Bus> buses = busRepository.findAll();
        if (!CollectionUtils.isEmpty(buses)) {
            return buses.stream().map(
                    bus ->  objectMapper.convertValue(bus, BusResponse.class)
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public BusResponse getBusDetails(Long id) throws BusNotFoundException {
        return busRepository.findById(id).map(
                bus -> objectMapper.convertValue(bus, BusResponse.class)
        ).orElseThrow( () -> new BusNotFoundException("Bus with id " + id + " could not be found"));
    }

    @Transactional
    public void saveBus(BusRequest request) {
        Bus bus = objectMapper.convertValue(request, Bus.class);
        if (!ObjectUtils.isEmpty(bus.getId())) {
            Bus busNeedUpdate = busRepository.findById(bus.getId()).get();
            busNeedUpdate.setBusStop(request.getBusStop());
            busNeedUpdate.setDistance(request.getDistance());
            busRepository.save(busNeedUpdate);
            return;
        }
        busRepository.save(bus);
    }

    @Transactional
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }


}
