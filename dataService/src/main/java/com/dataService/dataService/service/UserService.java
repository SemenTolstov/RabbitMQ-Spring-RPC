package com.dataService.dataService.service;

import com.dataService.dataService.entity.User;
import com.dataService.dataService.exception.ItemAlreadyExistException;
import com.dataService.dataService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void save(User inputUser) {
        try {

            if (userRepo.findUserByLogin(inputUser.getLogin()) != null
                    && userRepo.findUserByLogin(inputUser.getLogin()).getLogin().equalsIgnoreCase(inputUser.getLogin())
                    || userRepo.findUserByLogin(inputUser.getLogin()).getMail().equalsIgnoreCase(inputUser.getMail())) {
                throw new ItemAlreadyExistException("Item already exist");
            } else {
                userRepo.save(inputUser);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
