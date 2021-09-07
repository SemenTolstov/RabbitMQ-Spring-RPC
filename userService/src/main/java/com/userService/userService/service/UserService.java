package com.userService.userService.service;

import com.dataService.dto.ResponseDto;
import com.dataService.dto.UserDto;
import com.userService.userService.controller.UserController;
import com.userService.userService.dto.CreateUserRequest;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    public ResponseDto createUser(CreateUserRequest userRequest) {
        UserDto userDto = new UserDto(userRequest.getLogin(), userRequest.getPassword(), userRequest.getSurname(), userRequest.getName(), userRequest.getMail());
        logger.info(String.format("Emit '%s'", userRequest));
        return (ResponseDto) rabbitTemplate.convertSendAndReceive(exchange.getName(), "adding", userDto);
    }

    public ResponseDto getUserById(String id) {
        return (ResponseDto) rabbitTemplate.convertSendAndReceive(exchange.getName(), "getting", id);
    }
}
