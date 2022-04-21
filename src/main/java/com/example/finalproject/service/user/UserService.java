package com.example.finalproject.service.user;


import com.example.finalproject.persistence.model.UserDeleteRequest;
import com.example.finalproject.persistence.model.UserRequest;
import com.example.finalproject.persistence.model.UserUpdateRequest;

public interface UserService {
    //
    Boolean existsByUsername(UserRequest userRequest);

    Boolean existsByEmail(UserRequest userRequest);
//
    void createUser(UserRequest signUpRequest);

    void updateUser(UserUpdateRequest userUpdateRequest);

    void deleteUser(UserDeleteRequest userRequest);
}
