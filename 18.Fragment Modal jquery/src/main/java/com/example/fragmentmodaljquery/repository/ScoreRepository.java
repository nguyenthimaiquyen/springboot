package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Score;
import com.example.fragmentmodaljquery.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ScoreRepository {
    private static final String SCORE_DATA_FILE_NAME = "data/scores.json";
    public static int AUTO_ID = 1;
    private final FileUtil<Score> fileUtil;

    public List<Score> getAll() {
        return fileUtil.readDataFromFile(SCORE_DATA_FILE_NAME, Score[].class);
    }

    public List<Score> create(Score score) {
        List<Score> scores = getAll();
        if (CollectionUtils.isEmpty(scores)) {
            scores = new ArrayList<>();
        }
        scores.add(score);
        fileUtil.writeDataToFile(SCORE_DATA_FILE_NAME, scores);
        return scores;
    }




}
