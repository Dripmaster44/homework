package com.sparta.memo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegistrationRequestDto {

    private String username;
    private String password;

    private boolean admin = false;

    private String adminToken = "";

    public RegistrationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegistrationRequestDto(boolean admin) {
        this.admin = admin;
    }

    public RegistrationRequestDto(String adminToken) {
        this.adminToken = adminToken;
    }
}
