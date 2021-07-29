package com.example.finalproject.service;

import com.example.finalproject.models.User;

public interface UserService {
    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);

    User getUserByID(Long ID);
}
