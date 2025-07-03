package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String username;
    private String email;
    private String password;

    public UserUpdateDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
