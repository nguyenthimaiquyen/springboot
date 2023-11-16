package com.quyen.springthymeleaf.repository;

import com.quyen.springthymeleaf.entity.Assignment;
import com.quyen.springthymeleaf.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AssignmentRepository {
    private final FileUtil<Assignment> fileUtil;
    private static final String ASSIGNMENT_DATA_FILE_NAME = "assignments";

    public List<Assignment> getAll() {
        return fileUtil.readDataFromFile(ASSIGNMENT_DATA_FILE_NAME, Assignment[].class);
    }

    public List<Assignment> create(Assignment assignment) {
        List<Assignment> assignmentList = getAll();
        if (CollectionUtils.isEmpty(assignmentList)) {
            assignmentList = new ArrayList<>();
        }
        assignmentList.add(assignment);
        fileUtil.writeDataToFile(ASSIGNMENT_DATA_FILE_NAME, assignmentList);
        return assignmentList;
    }


}
