package com.dataService.dataService.repository;

import com.dataService.dataService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from USER_TABLE ut where ut.login = :inputLogin", nativeQuery = true)
    User findUserByLogin(@Param("inputLogin") String inputLogin);

    @Query(value = "select * from USER_TABLE ut where ut.mail = :inputMail", nativeQuery = true)
    User findUserByMail(String inputMail);

    @Query(value = "select * from USER_TABLE ut where ut.id = :id", nativeQuery = true)
    User findUserById(Integer id);
}
