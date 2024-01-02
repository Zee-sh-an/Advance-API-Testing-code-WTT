package com.day1api.controllers;

import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

     @GetMapping("/getAll")
    public ResponseEntity getAllUsers(){
         return  userService.getAllusers();
     }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody Users user){
        return userService.addUser(user);
    }


     @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId){
         return userService.deleteUser(userId);
     }

     @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@Valid @RequestBody Users user ,@PathVariable("userId") long userId){
         return userService.updateUser(user,userId);
     }

    @PatchMapping("/update/{userId}")
    public ResponseEntity updateUserByFields(@PathVariable long userId,@RequestBody Map<String,Object> fields){
         return userService.updateUserByFields(userId, fields);
    }


    @GetMapping("/search")
    public ResponseEntity search(@RequestParam (required = false)String email,@RequestParam(required = false)String mobNumber,@RequestParam(required = false) Status status,@RequestParam(required = false,defaultValue ="0") long id,@RequestParam(required = false,defaultValue = "0") long createdStartTime,@RequestParam(required = false,defaultValue = "0") long createdEndTime){
        return userService.userSearch(email,mobNumber,status,id,createdStartTime,createdEndTime);
    }
}
