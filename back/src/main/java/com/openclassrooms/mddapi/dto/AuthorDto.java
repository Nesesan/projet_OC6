package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {

    private long id;
    private String username;

    public AuthorDto(User author) {
        this.id = author.getId();
        this.username = author.getUsername();
    }
}

