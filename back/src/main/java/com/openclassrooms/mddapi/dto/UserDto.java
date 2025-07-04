package com.openclassrooms.mddapi.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserDto {

    private final String username;
    private final String email;
    private final List<TopicDto> SubscribedTopics;

    public  UserDto(String username, String email, List<TopicDto> SubscribedTopics) {
        this.username = username;
        this.email = email;
        this.SubscribedTopics = SubscribedTopics;
    }
}