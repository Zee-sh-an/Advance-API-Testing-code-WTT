package com.day1api.repo;

import com.day1api.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface UserRepsitory extends JpaRepository<Users,Long> {

//    @Query("select u from Users as u where u.email =:email")
//    List<Users>  getByEmail(@Param("email") String email);

//    @Query("select u from Users as u where u.mobNumber =:mobNum or u.id=:id or u.status=:status or u.email=:email")
//    List<Users> searchData(@Param("mobNum")String mobNum, @Param("id") long id, @Param("status") Status status, @Param("email") String email);


//    @Query("select u from Users as u where u.status =:status")
//    List<Users>  getByStatusActive(@Param("status") Status status);

//    @Query("select u from Users as u where u.id =:id")
//    List<Users> findById(@Param("id") long id);

//    @Query("select u from Users as u where u.date BETWEEN :StartDate AND :EndDate")
//    List<Users> getByDate(@Param("StartDate") Date StartDate,@Param("EndDate") Date EndDate);

    @Query("select u from Users as u where u.date BETWEEN :StartDate AND :EndDate")
    List<Users> getByDate(@Param("StartDate") Date StartDate, @Param("EndDate") Date EndDate);
}
