package com.userService.userService.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    static final String errorMessage = "must be not empty";

    @NotBlank(message = "login " + errorMessage)
    private String login;

    @NotBlank(message = "password " + errorMessage)
    private String password;

    @NotBlank(message = "surname " + errorMessage)
    private String surname;

    @NotBlank(message = "name " + errorMessage)
    private String name;

    @NotBlank(message = "mail " + errorMessage)
    private String mail;
}
