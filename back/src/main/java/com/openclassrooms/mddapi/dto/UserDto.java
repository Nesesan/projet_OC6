package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDto {

    private final String username;
    private final String email;
    private final List<Topic> SubscribedTopics;

    public  UserDto(String username, String email, List<Topic> SubscribedTopics) {
        this.username = username;
        this.email = email;
        this.SubscribedTopics = SubscribedTopics;
    }
}