package com.example.fragmentmodaljquery.entity;

import com.example.fragmentmodaljquery.statics.SubjectType;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column
    private Integer credit;

    @Column(name = "subject_type")
    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    private Score score;

}
