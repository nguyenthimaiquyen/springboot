package com.quyen.phanconglaixe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Integer driving; //số lượt lái

    @Column(name = "assignment_time")
    private Integer assignmentTime; //thời gian phân công (đơn vị tháng)

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "driver_id")
    private Driver driver;



}
