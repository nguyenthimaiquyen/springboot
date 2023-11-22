package com.example.fragmentmodaljquery.service;

import com.example.fragmentmodaljquery.entity.Score;
import com.example.fragmentmodaljquery.entity.Student;
import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.exception.StudentNotFoundException;
import com.example.fragmentmodaljquery.exception.SubjectNotFoundException;
import com.example.fragmentmodaljquery.model.request.ScoreCreationRequest;
import com.example.fragmentmodaljquery.repository.ScoreRepository;
import com.example.fragmentmodaljquery.repository.StudentRepository;
import com.example.fragmentmodaljquery.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScoreService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }

    public List<Score> create(ScoreCreationRequest request) throws StudentNotFoundException, SubjectNotFoundException {
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
        return scoreRepository.create(score);
    }

}
