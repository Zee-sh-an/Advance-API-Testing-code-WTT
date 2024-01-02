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

//     @PutMapping("/update/{userId}")
//    public ResponseEntity updateUser(@Valid @RequestBody Users user ,@PathVariable("userId") int userId){
//         return userService.updateUser(user,userId);
//     }


    @GetMapping("/search")
    public ResponseEntity search(@RequestParam (required = false)String email,@RequestParam(required = false)String mobNumber,@RequestParam(required = false) Status status,@RequestParam(required = false,defaultValue ="0") long id){
        return userService.userSearch(email,mobNumber,status,  id);
    }

//    @GetMapping("/searchByDate")
//    public ResponseEntity searchByTime(@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date StartDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date EndDate){
//        return userService.userSearchByDate(StartDate,EndDate);
//    }
@GetMapping("/searchByDate")
public ResponseEntity searchByDate(@RequestParam("StartDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") Date  StartDate, @RequestParam("EndDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") Date EndDate){
    return userService.userSearchByDate(StartDate,EndDate);
}
}
