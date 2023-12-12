package com.example.fragmentmodaljquery.repository;

import com.example.fragmentmodaljquery.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    List<Student> findByNameLikeIgnoreCase(String name);

    List<Student> findByNameLikeIgnoreCaseAndAddressLikeIgnoreCase(String name, String address);

    //JPQL
    @Query("select s from Student s where s.name like :name and s.address like :address")
    List<Student> searchByNameAndAddress(String name, String address);

    //native SQL
    @Query(value = "select s.* from Student s where s.name like :name and s.address like :address", nativeQuery = true)
    List<Student> searchByNameAndAddressVer2(String name, String address);

    @Modifying
    @Query("update Student s set s.name = :name where s.id = :id")
    void updateNameOfStudent(Long id, String name);

}
