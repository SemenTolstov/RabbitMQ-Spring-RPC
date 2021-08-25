package com.dataService.dataService.entity;

import com.dataService.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_TABLE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    private String password;

    private String surname;

    private String name;

    private String mail;

    private Date registered;

    public User(UserDto userDto) {
//        id = userDto.getId();
        login = userDto.getLogin();
        password = userDto.getPassword();
        surname = userDto.getSurname();
        name = userDto.getName();
        mail = userDto.getMail();
        registered = new Date();
    }
}
