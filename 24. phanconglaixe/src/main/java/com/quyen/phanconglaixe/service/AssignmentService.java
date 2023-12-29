package com.quyen.phanconglaixe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quyen.phanconglaixe.entity.Assignment;
import com.quyen.phanconglaixe.entity.Bus;
import com.quyen.phanconglaixe.entity.Driver;
import com.quyen.phanconglaixe.exception.AssignmentNotFoundException;
import com.quyen.phanconglaixe.exception.BusNotFoundException;
import com.quyen.phanconglaixe.exception.DriverNotFoundException;
import com.quyen.phanconglaixe.model.request.AssignmentRequest;
import com.quyen.phanconglaixe.model.response.AssignmentResponse;
import com.quyen.phanconglaixe.model.response.BusResponse;
import com.quyen.phanconglaixe.model.response.DriverResponse;
import com.quyen.phanconglaixe.repository.AssignmentRepository;
import com.quyen.phanconglaixe.repository.BusRepository;
import com.quyen.phanconglaixe.repository.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final ObjectMapper objectMapper;

    public List<AssignmentResponse> getAll() {
        List<Assignment> assignments = assignmentRepository.findAll();
        if (!CollectionUtils.isEmpty(assignments)) {
            return assignments.stream().map(
                    assignment -> AssignmentResponse.builder()
                            .id(assignment.getId())
                            .driver(DriverResponse.builder()
                                    .id(assignment.getDriver().getId())
                                    .name(assignment.getDriver().getName())
                                    .phone(assignment.getDriver().getPhone())
                                    .address(assignment.getDriver().getAddress())
                                    .level(assignment.getDriver().getLevel())
                                    .build()
                            )
                            .bus(BusResponse.builder()
                                    .id(assignment.getBus().getId())
                                    .distance(assignment.getBus().getDistance())
                                    .busStop(assignment.getBus().getBusStop())
                                    .build()
                            )
                            .driving(assignment.getDriving())
                            .assignmentTime(assignment.getAssignmentTime())
                            .build()
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public AssignmentResponse getAssignmentDetails(Long id) throws AssignmentNotFoundException {
        return assignmentRepository.findById(id).map(
                assignment -> AssignmentResponse.builder()
                        .id(assignment.getId())
                        .driver(DriverResponse.builder()
                                .id(assignment.getDriver().getId())
                                .name(assignment.getDriver().getName())
                                .phone(assignment.getDriver().getPhone())
                                .address(assignment.getDriver().getAddress())
                                .level(assignment.getDriver().getLevel())
                                .build())
                        .bus(BusResponse.builder()
                                .id(assignment.getBus().getId())
                                .distance(assignment.getBus().getDistance())
                                .busStop(assignment.getBus().getBusStop())
                                .build())
                        .driving(assignment.getDriving())
                        .assignmentTime(assignment.getAssignmentTime())
                        .build()
        ).orElseThrow(() -> new AssignmentNotFoundException("Assignment with id " + id + " could not be found"));

    }

    @Transactional
    public void saveAssignment(AssignmentRequest request) throws DriverNotFoundException, BusNotFoundException {
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow(
                () -> new DriverNotFoundException("Driver with id " + request.getDriverId() + " could not be found!")
        );
        Bus bus = busRepository.findById(request.getBusId()).orElseThrow(
                () -> new BusNotFoundException("Bus with id " + request.getBusId() + " could not be found!")
        );
        Assignment assignment = Assignment.builder()
                .id(request.getId())
                .driver(driver)
                .bus(bus)
                .driving(request.getDriving())
                .assignmentTime(request.getAssignmentTime())
                .build();
        if (!ObjectUtils.isEmpty(assignment.getId())) {
            //update
            Assignment assignmentNeedUpdate = assignmentRepository.findById(assignment.getId()).get();
            assignmentNeedUpdate.setDriver(driver);
            assignmentNeedUpdate.setBus(bus);
            assignmentNeedUpdate.setDriving(request.getDriving());
            assignmentNeedUpdate.setAssignmentTime(request.getAssignmentTime());
            assignmentRepository.save(assignmentNeedUpdate);
        }
        //create
        assignmentRepository.save(assignment);
    }
}
