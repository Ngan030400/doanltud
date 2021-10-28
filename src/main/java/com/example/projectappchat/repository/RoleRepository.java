package com.example.projectappchat.repository;

import com.example.projectappchat.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select ur.role.roleName from UserRole ur " +
            "where ur.user.userId = :userId" )
    List<String> getRoleNames(@Param("userId") Long userId);
}
