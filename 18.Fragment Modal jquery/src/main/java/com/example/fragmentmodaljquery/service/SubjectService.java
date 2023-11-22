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

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> getAll() {
        return subjectRepository.getAll();
    }

    public List<Subject> delete(int id) throws SubjectNotFoundException {
        return subjectRepository.delete(id);
    }

    public List<Subject> create(SubjectCreationRequest subjectCreationRequest) {
        Subject subject = Subject.builder()
                .id(subjectRepository.AUTO_ID++)
                .subjectName(subjectCreationRequest.getSubjectName())
                .credit(subjectCreationRequest.getCredit())
                .subjectType(subjectCreationRequest.getSubjectType())
                .build();
        return subjectRepository.create(subject);
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

    public List<Subject> update(SubjectUpdateRequest subject) throws SubjectNotFoundException {
        return subjectRepository.update(subject);
    }
}
