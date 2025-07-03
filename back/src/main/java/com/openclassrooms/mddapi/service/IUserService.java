package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    Optional<User> findByUsername(String username);
}
