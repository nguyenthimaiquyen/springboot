package com.quyen.test.entity;

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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private Float price;

    @Column(length = 1000)
    private String description;

    @Column
    private String image; //lưu tên ảnh

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Order> order;
}
