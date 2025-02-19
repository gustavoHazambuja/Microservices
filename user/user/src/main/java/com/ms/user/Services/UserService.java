package com.ms.user.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.Entities.User;
import com.ms.user.Producers.UserProducer;
import com.ms.user.Repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProducer userProducer;

    @Transactional
    public User saveUser(User user){
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);

        return user;
    }

    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Pageable pageable){

        Page<User> result = userRepository.findAll(pageable);

        return result;

    }

    @Transactional
    public void deleteUser(String email){

        userRepository.deleteByEmail(email);
    }
}
