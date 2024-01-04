package com.day1api.service;

import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.repo.UserRepsitory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.Assert;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepsitory userRepository;
    @Mock
    private Query query;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllusers() {

        List<Users> usersList=new ArrayList<>();
        usersList.add(new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date()));

        when(userRepository.findAll()).thenReturn(usersList);
        ResponseEntity responseEntity = userService.getAllusers();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void addUser() {
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.save(users)).thenReturn(users);
        ResponseEntity responseEntity= userService.addUser(users);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void deleteUser() {
        long id=1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.findById(id)).thenReturn(Optional.of(users));
        ResponseEntity responseEntity= userService.deleteUser(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void updateUser() {
        long id =1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.findById(id)).thenReturn(Optional.of(users));
        ResponseEntity responseEntity=userService.updateUser(users,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

//    @Test
//    public void updateUserByFields(){
//        long id = 1;
//        Users users = new Users(1, "zeeshan", "khan", "Agra", "1234567890", "zk@gmail.com", "qwerty", 50000, 50000, Status.ACTIVE, new Date());
//
//        Map<String, Object> fields=new HashMap<>();
//        fields.put("name","zeeshan");
//
////        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
////        when(userService.updateUserByFields(id,fields));
//        ResponseEntity responseEntity1 = userService.updateUserByFields(id,fields);
//        Assert.assertEquals(responseEntity1.getStatusCode(), HttpStatus.OK);
//    }

    @Test
    public void userSearch() {
        long id=1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",1703755537220l,0, Status.ACTIVE,new Date());

        ResponseEntity responseEntity=userService.userSearch("zk@gmail.com",null,null,0l,0l,0l);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }
}