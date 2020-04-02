package service;

import dao.AccountDao;
import dto.CreateAccountDto;
import dto.Response;
import exception.MobileBankException;
import model.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService {

    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Response<String> createAccount(CreateAccountDto createAccountDto) {
        try {
            String token = createAccountDto.getToken();
            Account account = new Account(createAccountDto.getAmount(), createAccountDto.getAccCode());
            accountDao.createAccount(token, account);
            return new Response<>("Account is successfully created", "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }

    public Response<List<String>> getUserAccountIds(String token) {
        try {
            return new Response<>(accountDao.getUserAccountIds(token), "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }

    public Response<List<String>> getUserAccountIdsByPhone(String phone) {
        try {
            return new Response<>(accountDao.getUserAccountIdsByPhone(phone), "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }
}
