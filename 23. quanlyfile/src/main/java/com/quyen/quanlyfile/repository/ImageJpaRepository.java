package com.quyen.quanlyfile.repository;

import com.quyen.quanlyfile.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<Image, Integer> {
}
