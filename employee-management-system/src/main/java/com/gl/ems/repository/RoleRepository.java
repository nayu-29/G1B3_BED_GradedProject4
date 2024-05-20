package com.gl.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ems.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
