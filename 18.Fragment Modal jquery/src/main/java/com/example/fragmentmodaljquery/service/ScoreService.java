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

    public List<ScoreDetailResponse> create(ScoreCreationRequest request) throws StudentNotFoundException, SubjectNotFoundException {
        Student student = studentRepository.findById(request.getStudentId());
        if (student == null) {
            throw new StudentNotFoundException("Không tìm thấy sinh viên!");
        }
        Subject subject = subjectRepository.findById(request.getSubjectId());
        if (subject == null) {
            throw new SubjectNotFoundException("Không tìm thấy môn học!");
        }

        Score score = Score.builder()
                .id(scoreRepository.AUTO_ID++)
                .student(student)
                .subject(subject)
                .testDate(request.getTestDate())
                .score(request.getScore())
                .build();
        List<Score> scores = scoreRepository.create(score);
        return scores.stream().map(
                s -> ScoreDetailResponse.builder()
                        .id(s.getId())
                        .student(s.getStudent())
                        .subject(s.getSubject())
                        .testDate(s.getTestDate())
                        .score(s.getScore())
                        .build()
        ).collect(Collectors.toList());
    }

}
