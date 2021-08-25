package com.dataService.dataService.repository;

import com.dataService.dataService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from USER_TABLE u where u.login = 'inputLogin'", nativeQuery = true)
    User findUserByLogin(String inputLogin);
}
