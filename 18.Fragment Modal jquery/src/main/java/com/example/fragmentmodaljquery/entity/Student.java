package com.example.fragmentmodaljquery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(name = "class_name")
    private String className;

    @Column
    private Float gpa;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Score score;
}
