package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.model.request.StudentUpdateRequest;
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
public class StudentRepository {
    private static final String STUDENT_DATA_FILE_NAME = "data/students.json";
    public static int AUTO_ID = 9;
    private final FileUtil<Student> fileUtil;

    public List<Student> getAll() {
        return fileUtil.readDataFromFile(STUDENT_DATA_FILE_NAME, Student[].class);
    }

    public List<Student> delete(int id) throws StudentNotFoundException {
        List<Student> students = getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                fileUtil.writeDataToFile(STUDENT_DATA_FILE_NAME, students);
                return students;
            }
        }
        return null;
    }

    public void save(Student student) {
        List<Student> students = getAll();
        if (CollectionUtils.isEmpty(students)) {
            students = new ArrayList<>();
        }
        students.add(student);
        fileUtil.writeDataToFile(STUDENT_DATA_FILE_NAME, students);
    }


    public Student findById(int id) throws StudentNotFoundException {
        List<Student> students = getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        return students.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public List<Student> update(StudentUpdateRequest student) throws StudentNotFoundException {
        List<Student> students = getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.get(i).setName(student.getName());
                students.get(i).setAddress(student.getAddress());
                students.get(i).setPhone(student.getPhone());
                students.get(i).setClassName(student.getClassName());
                fileUtil.writeDataToFile(STUDENT_DATA_FILE_NAME, students);
                return students;
            }
        }
        return null;
    }

}
