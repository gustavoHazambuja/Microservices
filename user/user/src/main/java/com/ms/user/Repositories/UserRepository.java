package com.ms.user.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.user.Entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    

}
