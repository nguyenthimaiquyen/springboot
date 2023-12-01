package com.example.fragmentmodaljquery.service;


import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SubjectRequest;
import com.example.fragmentmodaljquery.model.response.SubjectDetailResponse;
import com.example.fragmentmodaljquery.model.response.SubjectTypeResponse;
import com.example.fragmentmodaljquery.repository.SubjectRepository;
import com.example.fragmentmodaljquery.statics.SubjectType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final ObjectMapper objectMapper;

    public List<SubjectDetailResponse> getAll() throws SubjectNotFoundException {
        List<Subject> subjects = subjectRepository.getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        return subjects.stream().map(
                subject -> SubjectDetailResponse.builder()
                    .id(subject.getId())
                    .subjectName(subject.getSubjectName())
                    .credit(subject.getCredit())
                    .subjectType(subject.getSubjectType())
                    .build()
        ).collect(Collectors.toList());

    }

    public void delete(Integer id) throws SubjectNotFoundException {
        List<Subject> subjects = subjectRepository.getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        subjects.removeIf(s -> s.getId() == id);
        subjectRepository.save(subjects);
    }

    public List<SubjectTypeResponse> getSubjectType() {
        return List.of(
                SubjectTypeResponse.builder()
                        .code(SubjectType.GENERAL.getCode()).name(SubjectType.GENERAL.getName()).build(),
                SubjectTypeResponse.builder()
                        .code(SubjectType.BASIC_MAJOR.getCode()).name(SubjectType.BASIC_MAJOR.getName()).build(),
                SubjectTypeResponse.builder()
                        .code(SubjectType.SPECIALIZED.getCode()).name(SubjectType.SPECIALIZED.getName()).build()
        );
    }

    public SubjectDetailResponse getSubjectDetails(Integer id) throws SubjectNotFoundException {
        List<Subject> subjects = subjectRepository.getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        return subjects.stream().filter(s -> s.getId() == id).findFirst().map(
                subject -> SubjectDetailResponse.builder()
                    .id(subject.getId())
                    .subjectName(subject.getSubjectName())
                    .credit(subject.getCredit())
                    .subjectType(subject.getSubjectType())
                    .build()
        ).get() ;
    }

    public void save(SubjectRequest request) throws SubjectNotFoundException {
        Subject subject = objectMapper.convertValue(request, Subject.class);
        if (!ObjectUtils.isEmpty(request.getId())) {
            subjectRepository.update(subject);
            return;
        }
        subjectRepository.add(subject);
    }
}
