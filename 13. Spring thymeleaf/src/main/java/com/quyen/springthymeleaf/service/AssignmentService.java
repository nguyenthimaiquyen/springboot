package com.quyen.springthymeleaf.service;

import com.quyen.springthymeleaf.entity.Assignment;
import com.quyen.springthymeleaf.repository.AssignmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;


    public List<Assignment> getAllAssignment() {
        return assignmentRepository.getAllAssignment();
    }

    public void create(Assignment assignment) {
        assignmentRepository.createAssignment(assignment);
    }

    public Assignment getAssignmentById(Integer id) {
        return assignmentRepository.getAssignmentByID(id);
    }

    public void updateAssignment(Assignment assignment) {
        assignmentRepository.updateAssignment(assignment);
    }

    public void deleteAssignmentById(Integer id) {
        assignmentRepository.deleteAssignmentByID(id);
    }
}
