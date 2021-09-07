package com.dataService.dataService.service;

import com.dataService.dataService.entity.User;
import com.dataService.dataService.exception.ItemAlreadyExistException;
import com.dataService.dto.ResponseDto;
import com.dataService.dto.UserDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToAddListener {

    @Autowired
    UserService userService;

    @RabbitListener(queues = "adding")
    public ResponseDto messageAddListener(UserDto userDtoReceived) {
        try {
            User item = new User(userDtoReceived);
            userService.save(item);
            Thread.sleep(3000);
            return new ResponseDto(true, userDtoReceived.toString());
        } catch (InterruptedException | ItemAlreadyExistException e) {
            return new ResponseDto(false, e.getMessage());
        }
    }
}
