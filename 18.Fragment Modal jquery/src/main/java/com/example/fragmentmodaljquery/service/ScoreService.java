package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Score;
import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.ScoreCreationRequest;
import com.example.fragmentmodaljquery.model.response.ScoreDetailResponse;
import com.example.fragmentmodaljquery.repository.ScoretJpaRepository;
import com.example.fragmentmodaljquery.repository.StudentJpaRepository;
import com.example.fragmentmodaljquery.repository.SubjectJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {
    private final StudentJpaRepository studentJpaRepository;
    private final SubjectJpaRepository subjectJpaRepository;
    private final ScoretJpaRepository scoretJpaRepository;

    public List<ScoreDetailResponse> getAll() {
        List<Score> scores = scoretJpaRepository.findAll();
        return scores.stream().map(
                score -> ScoreDetailResponse.builder()
                    .id(score.getId())
                    .student(score.getStudent())
                    .subject(score.getSubject())
                    .testDate(score.getTestDate())
                    .score(score.getScore())
                    .build()
                ).collect(Collectors.toList());
    }

    public void create(ScoreCreationRequest request) throws StudentNotFoundException, SubjectNotFoundException {
        Student student = studentJpaRepository.findById(request.getStudentId()).get();
        if (student == null) {
            throw new StudentNotFoundException("Student not found!");
        }
        Subject subject = subjectJpaRepository.findById(request.getSubjectId()).get();
        if (subject == null) {
            throw new SubjectNotFoundException("Subject not found!");
        }
        Score score = Score.builder()
                .student(student)
                .subject(subject)
                .testDate(request.getTestDate())
                .score(request.getScore())
                .build();
        scoretJpaRepository.save(score);
    }

}
