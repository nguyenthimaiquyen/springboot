package com.quyen.springthymeleaf.repository;

import com.quyen.springthymeleaf.entity.Assignment;
import com.quyen.springthymeleaf.entity.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AssignmentRepository {
    private List<Assignment> assignmentList = new ArrayList<>();
    private static int AUTO_ID = 1;


    public List<Assignment> getAllAssignment() {
        return this.assignmentList;
    }

    public void createAssignment(Assignment assignment) {
        Assignment newAssignment = Assignment.builder()
                .id(AUTO_ID++)
                .driver(assignment.getDriver())
                .bus(assignment.getBus())
                .driving(assignment.getDriving())
                .day(assignment.getDay())
                .build();
        assignmentList.add(newAssignment);
    }

    public Assignment getAssignmentByID(Integer id) {
        return assignmentList.stream().filter(d -> d.getId() == id).findFirst().get();
    }

    public void updateAssignment(Assignment assignment) {
        for (int i = 0; i < assignmentList.size(); i++) {
            if (assignmentList.get(i).getId() == assignment.getId()) {
                assignmentList.get(i).setBus(assignment.getBus());
                assignmentList.get(i).setDriver(assignment.getDriver());
                assignmentList.get(i).setDriving(assignment.getDriving());
                assignmentList.get(i).setDay(assignment.getDay());
                break;
            }
        }
    }

    public void deleteAssignmentByID(Integer id) {
        for (int i = 0; i < assignmentList.size(); i++) {
            if (assignmentList.get(i).getId() == id) {
                assignmentList.remove(i);
                break;
            }
        }
    }
}
