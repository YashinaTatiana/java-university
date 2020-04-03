package com.university.accounts.controller.impl;

import com.university.accounts.controller.UserController;
import com.university.accounts.dto.UserDto;
import com.university.accounts.entity.Credentials;
import com.university.accounts.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 */
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<String> loginIn(Credentials authenticationRequest) {
        return ResponseEntity.ok(userService.loginIn(authenticationRequest));
    }

    @Override
    public void createUser(UserDto user) {
        System.out.println("Hi, It's me!");
        userService.createUser(user);
    }
}
