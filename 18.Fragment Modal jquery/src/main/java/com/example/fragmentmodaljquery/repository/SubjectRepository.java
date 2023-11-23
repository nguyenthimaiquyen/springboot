package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SubjectUpdateRequest;
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

    public List<Subject> delete(int id) throws SubjectNotFoundException {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == id) {
                subjects.remove(i);
                fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects);
                return subjects;
            }
        }
        return null;
    }

    public List<Subject> create(Subject subject) {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            subjects = new ArrayList<>();
        }
        subjects.add(subject);
        fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects);
        return subjects;
    }


    public Subject findById(int id) throws SubjectNotFoundException {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        return subjects.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public List<Subject> update(SubjectUpdateRequest student) throws SubjectNotFoundException {
        List<Subject> subjects = getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == student.getId()) {
                subjects.get(i).setSubjectName(student.getSubjectName());
                subjects.get(i).setCredit(student.getCredit());
                subjects.get(i).setSubjectType(student.getSubjectType());
                fileUtil.writeDataToFile(SUBJECT_DATA_FILE_NAME, subjects);
                return subjects;
            }
        }
        return null;
    }
}
