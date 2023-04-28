package ru.gb.perov.Part3HW8.jwt.api;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String username;
    private String password;
}