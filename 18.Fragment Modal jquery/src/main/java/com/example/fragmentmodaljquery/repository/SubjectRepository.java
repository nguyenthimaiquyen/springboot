package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SubjectRepository {
    private static final String SUBJECT_DATA_FILE_NAME = "data/subjects.json";
    public static int AUTO_ID = 1;
    private final FileUtil<Subject> fileUtil;

    public List<Subject> getAll() {
        return fileUtil.readDataFromFile(SUBJECT_DATA_FILE_NAME, Subject[].class);
    }

    public Subject findById(int id) throws SubjectNotFoundException {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        return subjects.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public void save(List<Subject> subjects) {
        fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects);
    }

    public void update(Subject subject) throws SubjectNotFoundException {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == subject.getId()) {
                subjects.get(i).setSubjectName(subject.getSubjectName());
                subjects.get(i).setCredit(subject.getCredit());
                subjects.get(i).setSubjectType(subject.getSubjectType());
                break;
            }
        }
        fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects);
    }

    public void add(Subject subject) {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            subjects = new ArrayList<>();
        }
        subject.setId(AUTO_ID);
        AUTO_ID++;
        subjects.add(subject);
        fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects);
    }
}
