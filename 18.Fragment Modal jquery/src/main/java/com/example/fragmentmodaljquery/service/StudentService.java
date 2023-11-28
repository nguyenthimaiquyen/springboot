package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.StudentCreationRequest;
import com.example.fragmentmodaljquery.model.request.StudentUpdateRequest;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDetailResponse> getAll() throws StudentNotFoundException {
        List<Student> students = studentRepository.getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        return students.stream().map(
                student -> StudentDetailResponse.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .className(student.getClassName())
                        .build()
        ).collect(Collectors.toList());
    }

    public void delete(Integer id) throws StudentNotFoundException {
        List<Student> students = studentRepository.getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        students.removeIf(s -> s.getId() == id);
        studentRepository.save(students);
    }

    public void create(StudentCreationRequest studentCreationRequest) {
        Student student = Student.builder()
                .id(studentRepository.AUTO_ID++)
                .name(studentCreationRequest.getName())
                .address(studentCreationRequest.getAddress())
                .phone(studentCreationRequest.getPhone())
                .className(studentCreationRequest.getClassName())
                .build();
        studentRepository.save(student);
    }

    public void update(Integer id, StudentUpdateRequest request) throws StudentNotFoundException {
        List<Student> students = studentRepository.getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.get(i).setName(request.getName());
                students.get(i).setAddress(request.getAddress());
                students.get(i).setPhone(request.getPhone());
                students.get(i).setClassName(request.getClassName());
                break;
            }
        }
        studentRepository.save(students);
    }

    public StudentDetailResponse getStudentDetails(Integer id) throws StudentNotFoundException {
        List<Student> students = studentRepository.getAll();
        if (CollectionUtils.isEmpty(students)) {
            throw new StudentNotFoundException("Students not found");
        }
        return students.stream().filter(s -> s.getId() == id).findFirst()
                .map(student -> StudentDetailResponse.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .className(student.getClassName())
                        .build())
                .get();
    }
}
