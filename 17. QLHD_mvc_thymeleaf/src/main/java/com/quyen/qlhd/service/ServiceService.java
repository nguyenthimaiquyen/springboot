package com.quyen.qlhd.service;


import com.quyen.qlhd.exception.ServiceNotFoundException;
import com.quyen.qlhd.model.request.ServiceCreationRequest;
import com.quyen.qlhd.model.request.ServiceUpdateRequest;
import com.quyen.qlhd.model.response.ServiceDetailResponse;
import com.quyen.qlhd.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public List<com.quyen.qlhd.entity.Service> getAll() {
        return serviceRepository.getAll();
    }

    public List<com.quyen.qlhd.entity.Service> deleteService(int id) throws ServiceNotFoundException {
        return serviceRepository.delete(id);
    }

    public List<com.quyen.qlhd.entity.Service> createService(ServiceCreationRequest serviceCreationRequest) {
        com.quyen.qlhd.entity.Service service = com.quyen.qlhd.entity.Service.builder()
                .id(serviceRepository.AUTO_ID++)
                .name(serviceCreationRequest.getName())
                .price(serviceCreationRequest.getPrice())
                .unit(serviceCreationRequest.getUnit())
                .period(serviceCreationRequest.getPeriod())
                .build();
        return serviceRepository.createService(service);
    }

    public ServiceDetailResponse findById(int id) throws ServiceNotFoundException {
        com.quyen.qlhd.entity.Service service = serviceRepository.findById(id);
        return ServiceDetailResponse.builder()
                .id(service.getId())
                .name(service.getName())
                .price(service.getPrice())
                .unit(service.getUnit())
                .period(service.getPeriod())
                .build();
    }

    public List<com.quyen.qlhd.entity.Service> updateService(ServiceUpdateRequest service) throws ServiceNotFoundException {
        return serviceRepository.updateService(service);
    }
}
