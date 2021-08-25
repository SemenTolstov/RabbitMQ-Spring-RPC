package com.userService.userService.controller;

import com.dataService.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "user")
    public String publishUserDetails(UserDto userDto) {

        try {
            String jsonString = objectMapper.writeValueAsString(userDto);
            logger.info(String.format("Emit '%s'", jsonString));
            String response = (String) rabbitTemplate.convertSendAndReceive("query",jsonString);
            logger.info(String.format("Received on producer '%s'", response));
            return "returned from worker : " + response;
            //return new UserCreateResponseDto(Integer id,)
        } catch (Exception e) {
           e.printStackTrace();
           return e.getMessage();
        }
    }
}
