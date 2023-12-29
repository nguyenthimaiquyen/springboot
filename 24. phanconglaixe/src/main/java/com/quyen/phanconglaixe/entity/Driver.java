package com.quyen.phanconglaixe.entity;

import com.quyen.phanconglaixe.statics.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "drivers")
public class Driver {
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

    @Column
    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.MERGE)
    private List<Assignment> assignments;

}
