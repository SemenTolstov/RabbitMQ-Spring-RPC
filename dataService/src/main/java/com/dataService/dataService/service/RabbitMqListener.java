package com.dataService.dataService.service;

import com.dataService.dataService.entity.User;
import com.dataService.dataService.repository.UserRepo;
import com.dataService.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMqListener {


    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = "query")
    public String worker(String message) {
        try {

            UserDto userDtoReceived = objectMapper.readValue(message, UserDto.class);
            User item = new User(userDtoReceived);
            userService.save(item);
            System.out.println("Order processed & persisted in DB " + item.getId());
            Thread.sleep(1000);
            return "Received on worker : " + message;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Problem in processing order";
        }

    }
}
