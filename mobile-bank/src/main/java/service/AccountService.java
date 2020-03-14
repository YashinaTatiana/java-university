package service;

import dao.AccountDao;
import dto.AccountsResponseDto;
import dto.CreateAccountDto;
import dto.UserResponseDto;
import exception.MobileBankException;
import model.Account;

import java.sql.SQLException;

public class AccountService {

    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public UserResponseDto createAccount(CreateAccountDto createAccountDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        try {
            String token = createAccountDto.getToken();
            Account account = new Account(createAccountDto.getAmount(), createAccountDto.getAccCode());
            accountDao.createAccount(token, account);
            return userResponseDto;
        } catch (MobileBankException | SQLException ex) {
            userResponseDto.setError(ex.getMessage());
            return userResponseDto;
        }
    }

    public AccountsResponseDto getUserAccountIds(String token) {
        AccountsResponseDto dto = new AccountsResponseDto();
        try {
            dto.setAccountList(accountDao.getUserAccountIds(token));
            return dto;
        } catch (MobileBankException | SQLException ex) {
            dto.setError(ex.getMessage());
            return dto;
        }
    }

    public AccountsResponseDto getUserAccountIdsByPhone(String phone) {
        AccountsResponseDto dto = new AccountsResponseDto();
        try {
            dto.setAccountList(accountDao.getUserAccountIdsByPhone(phone));
            return dto;
        } catch (MobileBankException | SQLException ex) {
            dto.setError(ex.getMessage());
            return dto;
        }
    }
}
