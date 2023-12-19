package com.day1api.repo;

import com.day1api.models.Status;
import com.day1api.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepsitory extends JpaRepository<Users,Long> {

    @Query("select u from Users as u where u.email =:email")
    public List getByEmail(@Param("email") String email);

    @Query("select u from Users as u where u.mobNumber =:mobNum")
    public List getByMobileNumber(@Param("mobNum")String mobNum);


    @Query("select u from Users as u where u.status =:status")
    public List getByStatusActive(@Param("status") Status status);


}
