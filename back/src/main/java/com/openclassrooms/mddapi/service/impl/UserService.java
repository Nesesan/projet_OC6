package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.IUserService;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
