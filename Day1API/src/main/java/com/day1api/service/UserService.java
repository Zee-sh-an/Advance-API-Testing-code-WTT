package com.day1api.service;

import com.day1api.models.Error;
import com.day1api.models.Status;
import com.day1api.models.Users;
import com.day1api.repo.UserRepsitory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepsitory userRepsitory;

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
            user.setStatus(Status.ACTIVE);
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
            Optional<Users> returnUser = userRepsitory.findById(id);
            if (returnUser.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Sorry User not found").build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            Users oldData=returnUser.get();
//            if (user.getName().isEmpty()){
//                user.setName(oldData.getName());
//            }
//
//            if (user.getLastName().isEmpty()){
//                user.setLastName(oldData.getLastName());
//            }
//
//            if (user.getAddress().isEmpty()){
//                user.setAddress(oldData.getAddress());
//            }
//
//            if (user.getDate()==null){
//                user.setDate(oldData.getDate());
//            }
//
//            if (user.getEmail().isEmpty()){
//                user.setName(oldData.getEmail());
//            }
//
//            if (user.getPassword().isEmpty()){
//                user.setPassword(oldData.getPassword());
//            }
//
//            if (user.getMobNumber().isEmpty()){
//                user.setMobNumber(oldData.getMobNumber());
//            }


            user.setCreatedTime(oldData.getCreatedTime());
            user.setModifiedTime(new Date().getTime());
            user.setId(id);
            Users updatedUser = userRepsitory.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("User updation Failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateUserByFields(long id, Map<String, Object> fields) {
        try {

            Optional<Users> existingUser = userRepsitory.findById(id);

            if (existingUser.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("User not found").build();
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            if (existingUser.isPresent()) {
                fields.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(Users.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingUser.get(), value);
                });
            }
            Users user = userRepsitory.save(existingUser.get());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            Error error= Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fields update failed").build();
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity userSearch(String email,String mobNumber,Status status,Long id,long createdStartTime,long createdEndTime) {

        try {
            Query query=null;
            Map<String,Object> params=new HashMap<>();
            boolean addClause = false;
            StringBuilder sb=new StringBuilder();
            sb.append("select u from Users u ");


            List<Users> usersList;


            if (email!=null){
                ifAddClauseRequired(addClause,sb);
                sb.append(" u.email= '" + email+"'");
                    addClause =true;

            }
            if (status!=null){
                ifAddClauseRequired(addClause,sb);
                    sb.append(" u.status= " + status);
                addClause=true;
            }

            if (id!=0){
                ifAddClauseRequired(addClause,sb);{
                    sb.append(" u.id = " + id);
                }
                addClause=true;
            }
            if (mobNumber!=null){
                ifAddClauseRequired(addClause,sb);{
                    sb.append(" u.mobNumber = '" +mobNumber+"'");
                }
                addClause=true;
            }

            if (createdStartTime!=0&&createdEndTime!=0){
                ifAddClauseRequired(addClause,sb);{
                    sb.append(" u.createdTime BETWEEN " +createdStartTime+ " AND " +createdEndTime);
                }
                addClause=true;
            }
                query=entityManager.createQuery(sb.toString());

                usersList=  query.getResultList();

            if (usersList.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("User not found").build();
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(usersList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Fail to find user by email").build();
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
    }

    private void ifAddClauseRequired(boolean addClause,StringBuilder sb){
        if (!addClause){
            sb.append(" Where");
        }else {
            sb.append(" AND");
        }
    }
}