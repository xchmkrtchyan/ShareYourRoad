package com.example.finalproject.persistence.role.model;

import com.example.finalproject.persistence.user.model.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "roles",schema = "final_project")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private UserRole name;

	public Role() {

	}

	public Role(UserRole name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public UserRole getName() {
		return name;
	}

	public void setName(UserRole name) {
		this.name = name;
	}
}