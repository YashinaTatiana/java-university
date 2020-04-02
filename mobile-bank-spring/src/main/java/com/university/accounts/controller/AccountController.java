package com.university.accounts.controller;

import com.university.accounts.dto.CreateAccountDto;
import com.university.accounts.entity.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Account controller")
@RequestMapping("/account")
public interface AccountController {

    @PostMapping()
    @ApiOperation("Create account")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Account is created successfully")
    })
    public void createAccount(@RequestBody @Valid CreateAccountDto currency);

    @GetMapping("/user-info/{id}")
    @ApiOperation("Get user accounts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get user accounts")
    })
    public ResponseEntity<List<Account>> getAccounts(@PathVariable("id") Long id);

    @GetMapping("/info/default/{userId}")
    @ApiOperation("Get default user account")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get default account"),
    })
    public ResponseEntity<Account> getDefaultAccount(@PathVariable("userId") Long userId);

}
