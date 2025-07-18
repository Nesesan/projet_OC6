package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserUpdateDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
        boolean usernameExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (emailExists || usernameExists) {
            throw new IllegalArgumentException("Email or Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getEmail(),
                        user.getSubscribedTopics().stream()
                                .map(this::convertTopicToDto)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new UserDto(
                        user.getUsername(),
                        user.getEmail(),
                        user.getSubscribedTopics().stream()
                                .map(this::convertTopicToDto)
                                .collect(Collectors.toList())
                ));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(String currentEmail, UserUpdateDto userUpdateDto) {
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'email actuel"));

        if (userUpdateDto.getEmail() != null && !userUpdateDto.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(userUpdateDto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("L'email est déjà utilisé par un autre utilisateur");
            }
            user.setEmail(userUpdateDto.getEmail());
        }

        if (userUpdateDto.getUsername() != null && !userUpdateDto.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(userUpdateDto.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Le nom d'utilisateur est déjà utilisé");
            }
            user.setUsername(userUpdateDto.getUsername());
        }

        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }

        System.out.println("Update user id: " + user.getId() + " | email: " + user.getEmail());

        return userRepository.save(user);
    }


    @Override
    @Transactional
    public void subscribeToTopic(User user, Topic topic) {
        if(!user.getSubscribedTopics().contains(topic)){
            user.getSubscribedTopics().add(topic);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void unsubscribeFromTopic(User user, Topic topic) {
        if(user.getSubscribedTopics().contains(topic)){
            user.getSubscribedTopics().remove(topic);
            userRepository.save(user);
        }

    }

    // Conversion Topic -> TopicDto
    private TopicDto convertTopicToDto(Topic topic) {
        TopicDto dto = new TopicDto();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        return dto;
    }
}
