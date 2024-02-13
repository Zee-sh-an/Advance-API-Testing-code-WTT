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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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
    public void getAllUsersException(){

        when(userRepository.findAll()).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = userService.getAllusers();
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void getAllUsersEmpty(){

        when(userRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity responseEntity = userService.getAllusers();
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void addUser() {
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.save(users)).thenReturn(users);
        ResponseEntity responseEntity= userService.addUser(users);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.CREATED);
    }

    @Test
    public void addUserException(){

        Users user=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());


        when(userRepository.save(user)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = userService.addUser(user);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteUserSuccess() {
        long id=1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.findById(id)).thenReturn(Optional.of(users));
        ResponseEntity responseEntity= userService.deleteUser(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void testDeleteUserNotFound() {
        long id=1;

        when(userRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity responseEntity= userService.deleteUser(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteUserException(){
        long id =1;
        Users user=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());


        when(userRepository.findById(id)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = userService.deleteUser(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testUpdateUserSuccess() {
        long id =1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.findById(id)).thenReturn(Optional.of(users));
        ResponseEntity responseEntity=userService.updateUser(users,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void testUpdateUserNotFound() {
        long id =1;
        Users users=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity responseEntity=userService.updateUser(users,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void UpdateUserException(){

        long id =1;
        Users user=new Users(1,"zeeshan","khan","Agra","1234567890","zk@gmail.com","qwerty",50000,50000, Status.ACTIVE,new Date());

        when(userRepository.findById(id)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = userService.updateUser(user,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void updateUserByFieldsSuccess(){
        long id = 1;
        Map<String, Object> fields=new HashMap<>();
        fields.put("name","zeeshan");

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(userRepository.findById(id)).thenReturn(Optional.of(new Users()));
        ResponseEntity responseEntity1 = userService.updateUserByFields(id,fields);
        Assert.assertEquals(responseEntity1.getStatusCode(),responseEntity.getStatusCode());
    }

    @Test
    public void updateUserByFieldsEmpty(){
        long id = 1;

        Map<String, Object> fields=new HashMap<>();
        fields.put("name","zeeshan");

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity responseEntity1 = userService.updateUserByFields(id,fields);
        Assert.assertEquals(responseEntity1.getStatusCode(),responseEntity.getStatusCode());
    }

    @Test
    public void updateUserByFieldsException(){
        long id = 1;

        Map<String, Object> fields=new HashMap<>();
        fields.put("name","zeeshan");

        when(userRepository.findById(id)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = userService.updateUserByFields(id,fields);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testUserSearchByEmail() {
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch("test@test.com", null, null, 0l, 0, 0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testUserSearchById(){
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response =userService.userSearch(null,null,null,1l,0,0);
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void UserSearchByStatus() {

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch(null, null, Status.ACTIVE, 0l, 0, 0);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUserSearchByCreatedStartTime(){

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch(null, null,null, 0l, 1707750643895l, 0);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUserSearchByCreatedEndTime(){

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch(null, null,null, 0l,0, 1707750643895l);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUserSearchByEmailandStatus(){

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch("zk@gmail.com", null,Status.ACTIVE,0l,0,0);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUserSearchByCreatedStartTimeandCreatedEndTime(){

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch(null, null,null,0l,1707750643895l,1707800668917l);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void UserSearchByMobileNumber(){

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(new Users()));
        ResponseEntity response = userService.userSearch(null, "7533851123",null,0l,0,0);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void UserSearchByUserNotFound(){

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity response = userService.userSearch(null, "7533851123",null,0l,0,0);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void userSearchException(){

        when(entityManager.createQuery(anyString())).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity=userService.userSearch("test@test.com",null,null,0l,0l,0l);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,responseEntity.getStatusCode());
    }

}