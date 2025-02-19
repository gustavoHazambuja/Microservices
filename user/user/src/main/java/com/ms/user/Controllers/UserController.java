package com.ms.user.Controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.DTOS.UserDTO;
import com.ms.user.Entities.User;
import com.ms.user.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDTO userDTO){

        var user = new User();
        BeanUtils.copyProperties(userDTO, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable){

        Page<User> result = userService.getAllUsers(pageable);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteByEmail/{email}")
    public void deleteUser(@PathVariable String email){

        userService.deleteUser(email);
    }
}
