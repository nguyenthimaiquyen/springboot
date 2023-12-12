package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Score;
import com.example.fragmentmodaljquery.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScoretJpaRepository extends JpaRepository<Score, Long> {


}
