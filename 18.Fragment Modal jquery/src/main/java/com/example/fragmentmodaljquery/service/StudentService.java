package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.model.request.SearchStudentRequest;
import com.example.fragmentmodaljquery.model.request.StudentRequest;
import com.example.fragmentmodaljquery.model.response.StudentDetailResponse;
import com.example.fragmentmodaljquery.repository.StudentJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final ObjectMapper objectMapper;
    private final StudentJpaRepository studentJpaRepository;

    public List<StudentDetailResponse> searchStudent(SearchStudentRequest request) {
        List<Student> students;
        if (request.getName() == null || request.getName().trim().equals("")) {
            students = studentJpaRepository.findAll();
        } else {
//            students = studentJpaRepository.findByName(request.getName());
//            students = studentJpaRepository.findByNameLikeIgnoreCase("%" + request.getName() + "%");
            students = studentJpaRepository.searchByNameAndAddress(
                    "%" + request.getName() + "%",
                    "%" + request.getAddress() + "%");
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

    public List<StudentDetailResponse> getAll() {
        List<Student> students = studentJpaRepository.findAll();
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

    public void delete(Long id) {
        studentJpaRepository.deleteById(id);
//        List<Student> students = studentRepository.getAll();
//        if (CollectionUtils.isEmpty(students)) {
//            throw new StudentNotFoundException("Students not found");
//        }
//        students.removeIf(s -> s.getId() == id);
//        studentRepository.save(students);
    }

    @Transactional
    public void save(StudentRequest request) {
        Student student = objectMapper.convertValue(request, Student.class);
        if (!ObjectUtils.isEmpty(request.getId())) {
//            Optional<Student> studentOptional = studentJpaRepository.findById(request.getId());
//            Student studentNeedUpdate = studentOptional.get();
//            studentNeedUpdate.setName(request.getName());
//            studentNeedUpdate.setPhone(request.getPhone());
//            studentNeedUpdate.setAddress(request.getAddress());
//            studentNeedUpdate.setClassName(request.getClassName());
//            studentJpaRepository.save(studentNeedUpdate);
            studentJpaRepository.updateNameOfStudent(request.getId(), request.getName());
            return;
        }
        studentJpaRepository.save(student);
    }

    public StudentDetailResponse getStudentDetails(Long id) throws StudentNotFoundException {
//        Optional<Student> studentOptional = studentJpaRepository.findById(id);
//        if (studentOptional.isEmpty()) {
//            throw new StudentNotFoundException("Student with id " + id + " could not be found");
//        }
//        Student student = studentOptional.get();
//        return StudentDetailResponse.builder()
//                .id(student.getId())
//                .name(student.getName())
//                .address(student.getAddress())
//                .phone(student.getPhone())
//                .className(student.getClassName())
//                .build();

        return studentJpaRepository.findById(id).map(
                student -> StudentDetailResponse.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .address(student.getAddress())
                        .phone(student.getPhone())
                        .className(student.getClassName())
                        .build()
        ).orElseThrow( () -> new StudentNotFoundException("Student with id " + id + " could not be found"));

    }


}
