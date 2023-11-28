package com.example.fragmentmodaljquery.service;


import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.SubjectCreationRequest;
import com.example.fragmentmodaljquery.model.request.SubjectUpdateRequest;
import com.example.fragmentmodaljquery.model.response.SubjectDetailResponse;
import com.example.fragmentmodaljquery.model.response.SubjectTypeResponse;
import com.example.fragmentmodaljquery.repository.SubjectRepository;
import com.example.fragmentmodaljquery.statics.SubjectType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

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

    public void create(SubjectCreationRequest request) {
        Subject subject = Subject.builder()
                .id(subjectRepository.AUTO_ID++)
                .subjectName(request.getSubjectName())
                .credit(request.getCredit())
                .subjectType(request.getSubjectType())
                .build();
        subjectRepository.save(subject);

    }

    public void update(Integer id, SubjectUpdateRequest request) throws SubjectNotFoundException {
        List<Subject> subjects = subjectRepository.getAll();
        if (CollectionUtils.isEmpty(subjects)) {
            throw new SubjectNotFoundException("Subjects not found");
        }
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getId() == id) {
                subjects.get(i).setSubjectName(request.getSubjectName());
                subjects.get(i).setCredit(request.getCredit());
                subjects.get(i).setSubjectType(request.getSubjectType());
                break;
            }
        }
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

}
