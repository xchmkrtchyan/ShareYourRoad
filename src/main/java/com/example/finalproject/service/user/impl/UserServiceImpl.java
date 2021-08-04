package com.example.finalproject.service.user.impl;

import com.example.finalproject.persistence.user.model.UserRole;
import com.example.finalproject.persistence.role.model.Role;
import com.example.finalproject.persistence.user.model.User;
import com.example.finalproject.rest.model.UserRequest;
import com.example.finalproject.persistence.role.RoleRepository;
import com.example.finalproject.persistence.user.UserRepository;
import com.example.finalproject.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private  final UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository, PasswordEncoder encoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existsByUsername(UserRequest userRequest) {
        return userRepository.existsByUsername(userRequest.getUsername());
    }

    @Override
    public Boolean existsByEmail(UserRequest userRequest) {
        return userRepository.existsByEmail(userRequest.getEmail());
    }

    public void createUser(UserRequest userRequest) {
        User user = new User(userRequest.getUsername(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getPhone(),
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword()));

        Set<String> strRoles = userRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(UserRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        break;
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
