package com.example.finalproject.service.user.impl;

import com.example.finalproject.repository.RoleRepository;
import com.example.finalproject.persistence.model.role.model.Role;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.persistence.model.user.model.User;
import com.example.finalproject.persistence.model.user.model.UserRole;
import com.example.finalproject.persistence.model.UserDeleteRequest;
import com.example.finalproject.persistence.model.UserRequest;
import com.example.finalproject.persistence.model.UserUpdateRequest;
import com.example.finalproject.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {
        Optional<User> byUsername = userRepository.findByUsername(userUpdateRequest.getUsername());
        byUsername.ifPresent(user -> {
            if ((userUpdateRequest.getFirstname()) != null && !userUpdateRequest.getFirstname().equals("")){
                user.setFirstname(userUpdateRequest.getFirstname());
            }
            if ((userUpdateRequest.getLastname()) != null && !userUpdateRequest.getLastname().equals("")){
                user.setLastname(userUpdateRequest.getLastname());
            }
            if ((userUpdateRequest.getEmail()) != null && !userUpdateRequest.getEmail().equals("")){
                user.setEmail(userUpdateRequest.getEmail());
            }
            if ((userUpdateRequest.getPhone()) != null && !userUpdateRequest.getPhone().equals("")){
                user.setPhone(userUpdateRequest.getPhone());
            }
            if ((userUpdateRequest.getPassword()) != null && !userUpdateRequest.getPassword().equals("")){
                user.setPassword(encoder.encode(userUpdateRequest.getPassword()));
            }
        });
        userRepository.save(byUsername.get());
    }

    @Override
    public void deleteUser(UserDeleteRequest userRequest) {
        Optional<User> byUsername = userRepository.findByUsername(userRequest.getUsername());
        User user = byUsername.get();
        userRepository.delete(user);
    }

    public void createUser(UserRequest userRequest) {
        User user = new User(userRequest.getUsername(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getPhone(),
                userRequest.getEmail(),
                encoder.encode(userRequest.getPassword()), userRequest.getGender());

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
