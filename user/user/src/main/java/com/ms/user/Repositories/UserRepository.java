package com.ms.user.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.Entities.User;


public interface UserRepository extends JpaRepository<User, UUID> {
    
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.email = :email")
    void deleteByEmail(String email);
}
