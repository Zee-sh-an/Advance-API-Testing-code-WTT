package com.day1api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

//    @NotNull annotation works when you simply give data null without collen
    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "LastName can not be null")
    @NotBlank(message = "LastName can not be blank")
    private String lastName;

    @NotNull(message = "Address can not be null")
    @NotBlank(message = "Address can not be blank")
    private String address;

    @Size(min = 10 ,max = 10,message = "invalid Number")
    private String mobNumber;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email !!")
    private String email;

    @Size(min = 6,max =15,message = "Password contain minimum 6 and maximum 15 characters")
    private String password;

    private long createdTime=new Date().getTime();

    private long modifiedTime;

    private Status status=Status.ACTIVE;

    private Date date=new Date();
}
