package com.example.dinhtrongkhang.repository;

import com.example.dinhtrongkhang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
