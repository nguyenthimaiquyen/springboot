package com.quyen.phanconglaixe.repository;


import com.quyen.phanconglaixe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
