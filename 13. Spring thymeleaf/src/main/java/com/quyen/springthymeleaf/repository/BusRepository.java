package com.quyen.springthymeleaf.repository;

import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.utils.BusReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusRepository {

    private List<Bus> busList;
    private BusReadFile BusFileReader;
    private static int AUTO_ID = 11;

    @Autowired
    public BusRepository(@Qualifier("BusJsonReadFile") BusReadFile fileReader) {
        this.BusFileReader = fileReader;
        this.busList = this.BusFileReader.readFile("classpath:static/buses.json");
    }

    public List<Bus> getAllBus() {
        return busList;
    }

    public void updateBus(Bus bus) {
        for (int i = 0; i < busList.size(); i++) {
            if (busList.get(i).getId() == bus.getId()) {
                busList.get(i).setDistance(bus.getDistance());
                busList.get(i).setBusStop(bus.getBusStop());
                break;
            }
        }
    }

    public Bus getBusById(Integer id) {
        return busList.stream().filter(d -> d.getId() == id).findFirst().get();
    }

    public void deleteBusById(Integer id) {
        for (int i = 0; i < busList.size(); i++) {
            if (busList.get(i).getId() == id) {
                busList.remove(i);
                break;
            }
        }
    }

    public void createBus(Bus bus) {
        Bus newBus = Bus.builder()
                .id(AUTO_ID++)
                .distance(bus.getDistance())
                .busStop(bus.getBusStop())
                .build();
        busList.add(newBus);
    }


}
