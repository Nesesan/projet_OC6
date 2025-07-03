package com.openclassrooms.mddapi.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    private String identifier;
    private String password;

}
