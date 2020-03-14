package dao;

import db.JdbcService;
import exception.MobileBankException;
import model.Account;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static exception.DbErrorCode.USER_IS_NOT_AUTHORIZED;
import static exception.DbErrorCode.USER_NOT_FOUND;
import static exception.MobileBankErrorCode.PHONE_IS_REQUIRED;

public class AccountDao {

    public void createAccount(String token, Account account)
            throws SQLException, MobileBankException {
        Long clientId = JdbcService.getUserIdByToken(token);
        if (clientId == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        account.setClientId(clientId);
        JdbcService.insertAccount(account);
    }

    public List<String> getUserAccountIds(String token)
            throws MobileBankException, SQLException {
        long userId = JdbcService.getUserIdByToken(token);
        if (token == null || token.trim().isEmpty() || (Long)userId == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        return JdbcService.getAccountsByUserId(userId)
                .stream().map(account -> account.getId().toString())
                .collect(Collectors.toList());
    }

    public List<String> getUserAccountIdsByPhone(String phone)
            throws MobileBankException, SQLException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new MobileBankException(PHONE_IS_REQUIRED);
        }
        User user = JdbcService.getUserByPhone(phone);
        if (null == user) {
            throw new MobileBankException(USER_NOT_FOUND);
        }
        long userId = user.getId();
        return JdbcService.getAccountsByUserId(userId)
                .stream().map(account -> account.getId().toString())
                .collect(Collectors.toList());
    }
}
