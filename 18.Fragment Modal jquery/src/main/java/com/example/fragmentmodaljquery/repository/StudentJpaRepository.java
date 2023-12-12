package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {


}
