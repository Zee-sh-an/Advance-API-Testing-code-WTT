package com.day1api.service;

import com.day1api.models.Error;
import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.repo.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepsitory userRepsitory;

    public ResponseEntity getById(long id) {
        try {

            Optional<Users> userBYId = userRepsitory.findById(id);
            if (userBYId.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry there is no user with this id").build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(userBYId, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fetchig of user by id failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAllusers() {

        try {
            List<Users> users = userRepsitory.findAll();
            if (users.isEmpty()) {

                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry there is no user").build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fetching of users failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity addUser(Users user) {

        try {
            if (ObjectUtils.isEmpty(user.getName())) {
                Error error = Error.builder().code(HttpStatus.NO_CONTENT.getReasonPhrase()).message("Sorry user is null").build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
//            throw new RuntimeException();
//            user.setStatus(Status.ACTIVE);
            Users user1 = userRepsitory.save(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("adding user failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity deleteUser(long id) {

        try {

            Optional<Users> userById = userRepsitory.findById(id);

            if (userById.isEmpty()) {

                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry there is no user of this id").build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

            }
//            UserService service= new UserService();
//            service.getById(id);

            userRepsitory.deleteById(id);
            Error error = Error.builder().code(HttpStatus.OK.getReasonPhrase()).message("Deleted Successfully").build();
            return new ResponseEntity<>(error, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Deletion of user failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateUser(Users user, long id) {

        try {
//            Optional<Users> userById = userRepsitory.findById(id);
//            if (userById.isEmpty()) {
//                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry User not found").build();
//                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//            }

            getById(id);

            user.setId(id);
            Users updatedUser = userRepsitory.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("User updation Failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity userByEmail(String email) {

        try {
            List<Users> usersList=new ArrayList<>();
            if (email!=null){
                usersList = userRepsitory.getByEmail(email);
            }

            if (usersList.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("User not found").build();
                return new ResponseEntity<>(error, HttpStatus.OK);
            }
            return new ResponseEntity<>(usersList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fail to find user by email").build();
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
    }

    public ResponseEntity userByMobileNumber(String mobNum) {

        try {

            List userByMobNum = userRepsitory.getByMobileNumber(mobNum);

            if (userByMobNum.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no user with this id").build();
                return new ResponseEntity<>(error, HttpStatus.OK);
            }
            return new ResponseEntity<>(userByMobNum, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fail to find user by mobile number").build();
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
    }

    public ResponseEntity userByStatus(Status status){

        try {

            List userStatus = userRepsitory.getByStatusActive(status);

            if (userStatus.isEmpty()){
                Error error= Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no user with this id").build();
                return new ResponseEntity<>(error,HttpStatus.OK);
            }
            return new ResponseEntity<>(userStatus,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fail to find user by Status").build();
            return new ResponseEntity<>(error,HttpStatus.OK);
        }

    }

}