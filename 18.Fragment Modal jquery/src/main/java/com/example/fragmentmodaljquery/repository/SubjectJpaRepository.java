package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Subject;
import com.example.fragmentmodaljquery.statics.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectJpaRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByCredit(Integer credit);

    List<Subject> findBySubjectNameLikeIgnoreCase(String subjectName);

    //JPQL
    @Query("select s from Subject s where s.subjectName like :subjectName and s.credit = :credit")
    List<Subject> findByNameAndCredit(String subjectName, Integer credit);

    //JPQL
    @Modifying
    @Query("update Subject s set s.subjectName = :subjectName, s.credit = :credit, s.subjectType = :subjectType where s.id = :id")
    void updateSubject(Long id, String subjectName, Integer credit, SubjectType subjectType);
}
