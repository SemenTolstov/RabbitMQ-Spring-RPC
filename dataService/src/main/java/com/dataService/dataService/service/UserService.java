package com.dataService.dataService.service;

import com.dataService.dataService.entity.User;
import com.dataService.dataService.exception.ItemAlreadyExistException;
import com.dataService.dataService.exception.ItemNotFoundException;
import com.dataService.dataService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void save(User inputUser) {
        User checkByLogin = userRepo.findUserByLogin(inputUser.getLogin());
        User checkByMail = userRepo.findUserByMail(inputUser.getMail());
        if (checkByLogin != null || checkByMail != null) {
            throw new ItemAlreadyExistException("Item already exist");
        }
        userRepo.save(inputUser);
    }

    public User getUser(Integer id) {
        User findById = userRepo.findUserById(id);
        if (findById == null) {
            throw new ItemNotFoundException("Item not found");
        }
        return userRepo.findUserById(id);
    }
}
