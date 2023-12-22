package com.day1api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private long id;

    private String name;

    private String lastName;

    private String address;

    @Size(min = 10 ,max = 10,message = "invalid Number")
    private String mobNumber;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Invalid email !!")
    private String email;

    @Size(min = 6,max =15)
    private String password;

    private long createdTime=new Date().getTime();

    private long modifiedTime;

    private Status status=Status.ACTIVE;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date=new Date();
}
