package com.example.finalproject.service.user;


import com.example.finalproject.rest.model.UserRequest;

public interface UserService {
    //
    Boolean existsByUsername(UserRequest userRequest);

    Boolean existsByEmail(UserRequest userRequest);
//
    void createUser(UserRequest signUpRequest);
}
