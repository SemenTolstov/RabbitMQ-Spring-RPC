package com.dataService.dataService.service;

import com.dataService.dataService.entity.User;
import com.dataService.dto.ResponseDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToGetListener {

    @Autowired
    UserService userService;

    @RabbitListener(queues = "getting")
    public ResponseDto messageGetListener(String message) {
        try {
            Integer getId = Integer.parseInt(message);
            User userToGet = userService.getUser(getId);
            return new ResponseDto(true, userToGet.toString());
        } catch (Exception e) {
            return new ResponseDto(false, e.getMessage());
        }
    }

}
