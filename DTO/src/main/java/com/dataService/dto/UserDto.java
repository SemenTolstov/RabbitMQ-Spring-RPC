package com.dataService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto { //createRequest(все кроме айди и даты) + UserCreateResponseDto(id + SUCCES)

//    private Integer id;

    private String login;

    private String password;

    private String surname;

    private String name;

    private String mail;

//    private Date registered;

}
