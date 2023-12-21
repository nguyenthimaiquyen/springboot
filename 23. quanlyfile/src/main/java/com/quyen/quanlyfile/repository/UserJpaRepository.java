package com.quyen.quanlyfile.repository;

import com.quyen.quanlyfile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
}
