package com.example.finalproject.service;


import com.example.finalproject.payload.request.SignupRequest;

public interface UserService {
    Boolean existsByUsername(SignupRequest signupRequest);

    Boolean existsByEmail(SignupRequest signupRequest);

    void createUser(SignupRequest signUpRequest);
}
