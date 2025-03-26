package com.ms.user.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import com.ms.user.dtos.UserRecordDto;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto  userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userRecordDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
}
