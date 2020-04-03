package com.university.accounts.controller;

import com.university.accounts.dto.UserDto;
import com.university.accounts.entity.Credentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "User controller")
@RestController
@RequestMapping("/v1/user")
public interface UserController {

    @PostMapping("/signin")
    @ApiOperation(value = "Log in user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully logged in"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<String> loginIn(@RequestBody @Valid Credentials authenticationRequest);

    @PostMapping("/signup")
    @ApiOperation(value = "Sign up user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully signing up"),
    })
    public void createUser(@RequestBody UserDto body);

}
