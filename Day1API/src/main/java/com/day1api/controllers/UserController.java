package com.day1api.controllers;

import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

     @GetMapping("/users")
    public ResponseEntity getAllUsers(){
         return  userService.getAllusers();
     }

     @GetMapping("/getById/{id}")
     public ResponseEntity getById(@PathVariable long id){
         return userService.getById(id) ;
     }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody Users user){

        return userService.addUser(user);
    }


     @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId){
         return userService.deleteUser(userId);
     }

     @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@Valid @RequestBody Users user ,@PathVariable("userId") int userId){
         return userService.updateUser(user,userId);
     }

     @GetMapping("/UserByEmail/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
         return userService.userByEmail(email);
     }

    @GetMapping("/UserByMobNum/{mobNum}")
    public ResponseEntity getUserByMobNum(@PathVariable String mobNum){
        return userService.userByMobileNumber(mobNum);
    }

    @GetMapping("/UserByStatus/{status}")
    public ResponseEntity getUserByStatus(@PathVariable Status status){
        return userService.userByStatus(status);
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam (required = false)String email){
        return userService.userByEmail(email);
    }
}
