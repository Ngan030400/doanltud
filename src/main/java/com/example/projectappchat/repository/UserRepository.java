package com.example.projectappchat.repository;

import com.example.projectappchat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();
    @Query(value = "SELECT u FROM User u where u.userAccount = :userAccount")
    User findUserAccount(@Param("userAccount") String userAccount);

//    @Query(select * from User u where
//            + u.user_name like %:keyword% or u.user_contact_number like %:keyword% or u.user_email like %:keyword%)

    @Query("select u from User u where u.userName like %:keyword%")
    List<User> search (@Param("keyword") String keyword);
}


