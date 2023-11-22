package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.model.request.StudentCreationRequest;
import com.example.fragmentmodaljquery.model.request.StudentUpdateRequest;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    public List<Student> delete(int id) throws StudentNotFoundException {
        return studentRepository.delete(id);
    }

    public List<Student> create(StudentCreationRequest studentCreationRequest) {
        Student student = Student.builder()
                .id(studentRepository.AUTO_ID++)
                .fullname(studentCreationRequest.getFullname())
                .address(studentCreationRequest.getAddress())
                .phone(studentCreationRequest.getPhone())
                .className(studentCreationRequest.getClassName())
                .build();
        return studentRepository.create(student);
    }

    public StudentDetailResponse findById(int id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id);
        return StudentDetailResponse.builder()
                .id(student.getId())
                .fullname(student.getFullname())
                .address(student.getAddress())
                .phone(student.getPhone())
                .className(student.getClassName())
                .build();
    }

    public List<Student> update(StudentUpdateRequest student) throws StudentNotFoundException {
        return studentRepository.update(student);
    }
}
