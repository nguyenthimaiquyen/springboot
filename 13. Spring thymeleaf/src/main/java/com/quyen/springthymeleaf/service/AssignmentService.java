package com.quyen.springthymeleaf.service;

import com.quyen.springthymeleaf.entity.Assignment;
import com.quyen.springthymeleaf.entity.Bus;
import com.quyen.springthymeleaf.entity.Driver;
import com.quyen.springthymeleaf.exception.BusNotFoundException;
import com.quyen.springthymeleaf.exception.DriverNotFoundException;
import com.quyen.springthymeleaf.model.request.AssignmentCreationRequest;
import com.quyen.springthymeleaf.repository.AssignmentRepository;
import com.quyen.springthymeleaf.repository.BusRepository;
import com.quyen.springthymeleaf.repository.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignmentService {
    public static int AUTO_ID = 1;
    private final AssignmentRepository assignmentRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;

    public List<Assignment> getAll() {
        return assignmentRepository.getAll();
    }

    public List<Assignment> create(AssignmentCreationRequest assignment) throws BusNotFoundException, DriverNotFoundException {
        Bus bus = busRepository.getById(assignment.getBusId());
        if (bus == null) {
            throw new BusNotFoundException("Không tìm thấy tuyến xe buýt!");
        }
        Driver driver = driverRepository.getById(assignment.getDriverId());
        if (driver == null) {
            throw new DriverNotFoundException("Không tìm thấy thông tin tài xế!");
        }
        Assignment newAssignment = Assignment.builder()
                .id(AUTO_ID++)
                .driver(driver)
                .bus(bus)
                .driving(assignment.getDriving())
                .assignmentTime(assignment.getAssignmentTime())
                .build();
        return assignmentRepository.create(newAssignment);
    }

}
