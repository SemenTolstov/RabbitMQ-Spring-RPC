package com.userService.userService.controller;

import com.dataService.dto.ResponseDto;
import com.userService.userService.dto.CreateUserRequest;
import com.userService.userService.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    Logger logger = Logger.getLogger(UserController.class);

    @PostMapping(value = "user")
    public ResponseEntity publishUserDetails(@Valid @RequestBody CreateUserRequest userRequest) {
        logger.info(String.format("Emit '%s'", userRequest));
        ResponseDto response = userService.createUser(userRequest);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getResult());
        }
        logger.info(String.format("Received on producer '%s'", response));
        return new ResponseEntity(response.getResult(), HttpStatus.OK);
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity getUserById(@PathVariable String id) {
        logger.info(String.format("Emit '%s'", id));
        ResponseDto response = userService.getUserById(id);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getResult());
        }
        logger.info(String.format("Received on producer '%s'", response));
        return new ResponseEntity(response.getResult(), HttpStatus.OK);
    }
}
