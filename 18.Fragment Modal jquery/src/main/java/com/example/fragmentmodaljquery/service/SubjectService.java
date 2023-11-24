package com.example.fragmentmodaljquery.service;


import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SubjectCreationRequest;
import com.example.fragmentmodaljquery.model.request.SubjectUpdateRequest;
import com.example.fragmentmodaljquery.model.response.SubjectDetailResponse;
import com.example.fragmentmodaljquery.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<SubjectDetailResponse> getAll() {
        List<Subject> subjects = subjectRepository.getAll();
        return subjects.stream().map(
                subject -> SubjectDetailResponse.builder()
                    .id(subject.getId())
                    .subjectName(subject.getSubjectName())
                    .credit(subject.getCredit())
                    .subjectType(subject.getSubjectType())
                    .build()
        ).collect(Collectors.toList());

    }

    public List<SubjectDetailResponse> delete(int id) throws SubjectNotFoundException {
        List<Subject> subjects = subjectRepository.delete(id);
        return subjects.stream().map(
                sub -> SubjectDetailResponse.builder()
                        .id(sub.getId())
                        .subjectName(sub.getSubjectName())
                        .credit(sub.getCredit())
                        .subjectType(sub.getSubjectType())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<SubjectDetailResponse> create(SubjectCreationRequest subjectCreationRequest) {
        Subject subject = Subject.builder()
                .id(subjectRepository.AUTO_ID++)
                .subjectName(subjectCreationRequest.getSubjectName())
                .credit(subjectCreationRequest.getCredit())
                .subjectType(subjectCreationRequest.getSubjectType())
                .build();
        List<Subject> subjects = subjectRepository.create(subject);
        return subjects.stream().map(
                sub -> SubjectDetailResponse.builder()
                        .id(sub.getId())
                        .subjectName(sub.getSubjectName())
                        .credit(sub.getCredit())
                        .subjectType(sub.getSubjectType())
                        .build()
        ).collect(Collectors.toList());
    }

    public SubjectDetailResponse findById(int id) throws SubjectNotFoundException {
        Subject subject = subjectRepository.findById(id);
        return SubjectDetailResponse.builder()
                .id(subject.getId())
                .subjectName(subject.getSubjectName())
                .credit(subject.getCredit())
                .subjectType(subject.getSubjectType())
                .build();
    }

    public List<SubjectDetailResponse> update(SubjectUpdateRequest subject) throws SubjectNotFoundException {
        List<Subject> subjects = subjectRepository.update(subject);
        return subjects.stream().map(
                sub -> SubjectDetailResponse.builder()
                        .id(sub.getId())
                        .subjectName(sub.getSubjectName())
                        .credit(sub.getCredit())
                        .subjectType(sub.getSubjectType())
                        .build()
        ).collect(Collectors.toList());
    }
}
