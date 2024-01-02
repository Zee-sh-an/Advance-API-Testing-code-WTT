package com.day1api.controllers;

import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getAllUsers() {
        List<Users> usersList=new ArrayList<>();
        usersList.add(new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date()));

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(userService.getAllusers()).thenReturn(responseEntity);
        ResponseEntity responseEntity1 = userController.getAllUsers();
        Assert.assertEquals(responseEntity1.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    public void addUser() {

        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        ResponseEntity responseEntity =new ResponseEntity<>(HttpStatus.OK);
        when(userController.addUser(users)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= userService.addUser(users);
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void deleteUser() {

        long id=1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        ResponseEntity responseEntity=new ResponseEntity<>(HttpStatus.OK);
        when(userService.deleteUser(id)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= userController.deleteUser(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void search() {

        long id=1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        ResponseEntity responseEntity =new ResponseEntity<>(HttpStatus.OK);
        when(userService.userSearch(null,null,null,id)).thenReturn(responseEntity);
        ResponseEntity responseEntity1=userController.search(null,null,null,id);
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void searchByDate() throws ParseException {

//        String dateString ="2023-12-27 18:19:01.545000";
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<Users> usersList=new ArrayList<>();
        usersList.add(new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,dateFormat.parse("2023-12-23 00:00:00.000000")));

        ResponseEntity responseEntity=new ResponseEntity<>(HttpStatus.OK);
        when(userService.userSearchByDate(dateFormat.parse("2023-12-21 00:00:00.000000"),dateFormat.parse("2023-12-27 18:19:01.545000"))).thenReturn(responseEntity);
        ResponseEntity responseEntity1=userController.searchByDate(dateFormat.parse("2023-12-21 00:00:00.000000"),dateFormat.parse("2023-12-27 18:19:01.545000"));
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
    }
}