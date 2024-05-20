package com.gl.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ems.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
