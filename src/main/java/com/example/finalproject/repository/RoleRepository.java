package com.example.finalproject.repository;

import com.example.finalproject.persistence.model.user.model.UserRole;
import com.example.finalproject.persistence.model.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRole name);
}
