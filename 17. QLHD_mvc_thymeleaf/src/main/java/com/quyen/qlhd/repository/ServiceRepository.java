package com.quyen.qlhd.repository;

import com.quyen.qlhd.entity.Service;
import com.quyen.qlhd.exception.ServiceNotFoundException;
import com.quyen.qlhd.model.response.ServiceDetailResponse;
import com.quyen.qlhd.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ServiceRepository {
    private static final String SERVICE_DATA_FILE_NAME = "services";
    public static int AUTO_ID = 11;
    private final FileUtil<Service> fileUtil;

    public List<Service> getAll() {
        return fileUtil.readDataFromFile(SERVICE_DATA_FILE_NAME, Service[].class);
    }

    public List<Service> delete(int id) throws ServiceNotFoundException {
        List<Service> services = getAll();
        if (CollectionUtils.isEmpty(services)) {
            throw new ServiceNotFoundException("Services not found");
        }
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getId() == id) {
                services.remove(i);
                fileUtil.writeDataToFile(SERVICE_DATA_FILE_NAME, services);
                return services;
            }
        }
        return null;
    }

    public List<Service> createService(Service service) {
        List<Service> services = getAll();
        if (CollectionUtils.isEmpty(services)) {
            services = new ArrayList<>();
        }
        services.add(service);
        fileUtil.writeDataToFile(SERVICE_DATA_FILE_NAME, services);
        return services;
    }


    public Service findById(int id) throws ServiceNotFoundException {
        List<Service> services = getAll();
        if (CollectionUtils.isEmpty(services)) {
            throw new ServiceNotFoundException("Customers not found");
        }
        return services.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public List<Service> updateService(ServiceDetailResponse service) throws ServiceNotFoundException {
        List<Service> services = getAll();
        if (CollectionUtils.isEmpty(services)) {
            throw new ServiceNotFoundException("Services not found");
        }
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getId() == service.getId()) {
                services.get(i).setName(service.getName());
                services.get(i).setPrice(service.getPrice());
                services.get(i).setUnit(service.getUnit());
                services.get(i).setPeriod(service.getPeriod());
                fileUtil.writeDataToFile(SERVICE_DATA_FILE_NAME, services);
                return services;
            }
        }
        return null;
    }


}
