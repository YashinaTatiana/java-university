package com.university.accounts.controller.impl;

import com.university.accounts.controller.AccountController;
import com.university.accounts.dto.CreateAccountDto;
import com.university.accounts.entity.Account;
import com.university.accounts.service.impl.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public void createAccount(CreateAccountDto currency) {
        accountService.createAccount(currency);
    }

    @Override
    public ResponseEntity<List<Account>> getAccounts(Long id) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(id));
    }

    @Override
    public ResponseEntity<Account> getDefaultAccount(Long userId) {
        return ResponseEntity.ok(accountService.getDefaultByUserId(userId));
    }
}
