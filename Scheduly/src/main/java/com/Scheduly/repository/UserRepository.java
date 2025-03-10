package com.Scheduly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Scheduly.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
