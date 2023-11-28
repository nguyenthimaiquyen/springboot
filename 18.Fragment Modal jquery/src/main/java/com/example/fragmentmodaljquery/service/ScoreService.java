package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Score;
import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.ScoreCreationRequest;
import com.example.fragmentmodaljquery.model.response.ScoreDetailResponse;
import com.example.fragmentmodaljquery.repository.ScoreRepository;
import com.example.fragmentmodaljquery.repository.StudentRepository;
import com.example.fragmentmodaljquery.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScoreRepository scoreRepository;

    public List<ScoreDetailResponse> getAll() {
        List<Score> scores = scoreRepository.getAll();
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
        Student student = studentRepository.findById(request.getStudentId());
        if (student == null) {
            throw new StudentNotFoundException("Student not found!");
        }
        Subject subject = subjectRepository.findById(request.getSubjectId());
        if (subject == null) {
            throw new SubjectNotFoundException("Subject not found!");
        }
        Score score = Score.builder()
                .id(scoreRepository.AUTO_ID++)
                .student(student)
                .subject(subject)
                .testDate(request.getTestDate())
                .score(request.getScore())
                .build();
        scoreRepository.save(score);
    }

}
