package com.university.accounts.service.impl;

import com.university.accounts.dao.AccountRepository;
import com.university.accounts.dao.UserRepository;
import com.university.accounts.dto.CreateAccountDto;
import com.university.accounts.entity.Account;
import com.university.accounts.entity.User;
import com.university.accounts.service.security.AuthenticationService;
import com.university.accounts.exceptions.BadRequest;
import com.university.accounts.exceptions.ErrorMessage;
import com.university.accounts.exceptions.ForbiddenException;
import com.university.accounts.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    public void createAccount(CreateAccountDto dto) throws ValidationException {
        User user = authenticationService.getAuthenticatedUser();
        Account account = new Account(dto);
        user.setAccountList(account);
        accountRepository.save(account);
    }

    public List<Account> getAccountsByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        return user.getAccountList();
    }

    public List<Account> getAccountsByPhone(String phone) throws ValidationException {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new NotFound(ErrorMessage.USER_NOT_FOUND.getMessage()));
        return user.getAccountList();
    }

    public Account getDefaultByUserPhone(String phone) throws ValidationException {
        return getAccountsByPhone(phone)
                .stream()
                .filter(a -> a.getIsDefault().equals(true))
                .findFirst()
                .orElseThrow(() -> new BadRequest(ErrorMessage.DEFAULT_ACCOUNT_NOT_FOUND.getMessage()));
    }

    public Account getDefaultByUserId(Long id) throws ValidationException {
        User user = authenticationService.getAuthenticatedUser();
        if (!user.getId().equals(id)) {
            throw new ForbiddenException(ErrorMessage.NOT_ALLOWED_TO_VIEW_ACCOUNT.getMessage());
        }
        return user.getAccountList()
                .stream()
                .filter(e -> e.getIsDefault().equals(TRUE))
                .findFirst()
                .orElseThrow(() -> new NotFound(ErrorMessage.DEFAULT_ACCOUNT_NOT_FOUND.getMessage()));
    }
}
