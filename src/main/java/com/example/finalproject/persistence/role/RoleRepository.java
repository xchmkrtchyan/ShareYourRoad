package com.example.finalproject.persistence.role;

import com.example.finalproject.persistence.user.model.UserRole;
import com.example.finalproject.persistence.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserRole name);
}
