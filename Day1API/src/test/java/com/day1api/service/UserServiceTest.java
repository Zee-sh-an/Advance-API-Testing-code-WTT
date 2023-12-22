package com.day1api.service;

import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.repo.UserRepsitory;
import org.junit.Assert;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepsitory userRepsitory;

    @InjectMocks
    private UserService userService;

    @Test
    public void getById() {

    }

//    @Test
//    public void getAllusers() {
//
//        List<Users> usersList=new ArrayList<>();
//        usersList.add(new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE));
//
//        when(userRepsitory.findAll()).thenReturn(usersList);
//        ResponseEntity responseEntity = userService.getAllusers();
//        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//    }

//    @Test
//    public void addUser() {
//        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE);
//
//        when(userRepsitory.save(users)).thenReturn(users);
//        ResponseEntity responseEntity= userService.addUser(users);
//        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
//    }

//    @Test
//    public void deleteUser() {
//        long id=1;
//        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE);
//
//        when(userRepsitory.findById(id)).thenReturn(Optional.of(users));
//        .
//
//    }

    public @Test
    void updateUser() {
    }

    public @Test
    void userSearch() {
    }

    public @Test
    void userByMobileNumber() {
    }

    public @Test
    void userByStatus() {
    }
}