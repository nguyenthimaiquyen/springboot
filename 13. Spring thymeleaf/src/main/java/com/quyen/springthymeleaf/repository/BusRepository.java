package com.quyen.springthymeleaf.repository;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.exception.BusNotFoundException;
import com.quyen.springthymeleaf.model.request.BusUpdateRequest;
import com.quyen.springthymeleaf.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BusRepository {
    private static final String BUS_DATA_FILE_NAME = "buses";
    private final FileUtil<Bus> fileUtil;


    public List<Bus> getAll() {
        return fileUtil.readDataFromFile(BUS_DATA_FILE_NAME, Bus[].class);
    }

    public List<Bus> update(BusUpdateRequest bus) throws BusNotFoundException {
        List<Bus> busList = getAll();
        if (CollectionUtils.isEmpty(busList)) {
            throw new BusNotFoundException("Buses not found");
        }
        Optional<Bus> busNeedUpdate = busList.stream().filter(d -> d.getId() == bus.getId()).findFirst();
        if (busNeedUpdate.isEmpty()) {
            throw new BusNotFoundException("Buses not found");
        }
        for (int i = 0; i < busList.size(); i++) {
            if (busList.get(i).getId() == bus.getId()) {
                busList.get(i).setDistance(bus.getDistance());
                busList.get(i).setBusStop(bus.getBusStop());
                fileUtil.writeDataToFile(BUS_DATA_FILE_NAME, busList);
                return busList;
            }
        }
        return null;
    }

    public Bus getById(int id) throws BusNotFoundException {
        List<Bus> busList = getAll();
        if (CollectionUtils.isEmpty(busList)) {
            throw new BusNotFoundException("Drivers not found");
        }
        return busList.stream().filter(d -> d.getId() == id).findFirst().get();
    }

    public List<Bus> deleteById(int id) throws BusNotFoundException {
        List<Bus> busList = getAll();
        if (CollectionUtils.isEmpty(busList)) {
            throw new BusNotFoundException("Drivers not found");
        }
        for (int i = 0; i < busList.size(); i++) {
            if (busList.get(i).getId() == id) {
                busList.remove(i);
                fileUtil.writeDataToFile(BUS_DATA_FILE_NAME, busList);
                return busList;
            }
        }
        return null;
    }

    public List<Bus> create(Bus bus) {
        List<Bus> busList = getAll();
        if (CollectionUtils.isEmpty(busList)) {
            busList = new ArrayList<>();
        }
        busList.add(bus);
        fileUtil.writeDataToFile(BUS_DATA_FILE_NAME, busList);
        return busList;
    }


}
