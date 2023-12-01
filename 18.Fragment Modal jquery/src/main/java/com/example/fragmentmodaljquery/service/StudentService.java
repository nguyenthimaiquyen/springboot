package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.model.request.StudentRequest;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

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

    public void save(StudentRequest request) throws StudentNotFoundException {
        Student student = objectMapper.convertValue(request, Student.class);
        if (!ObjectUtils.isEmpty(request.getId())) {
            studentRepository.update(student);
            return;
        }
        studentRepository.add(student);
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
