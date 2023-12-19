package com.day1api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Users {

    @Id
    long id;

    String name;

    String lastName;

    String address;

    @Size(min = 0 ,max = 10,message = "invalid Number")
    String mobNumber;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email !!")
    String email;

    @Size(min = 6,max =15)
    String password;

    long createdTime=new Date().getTime();

    long modifiedTime;

    Status status=Status.ACTIVE;
}
