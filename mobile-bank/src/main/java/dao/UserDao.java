package dao;

import db.JdbcService;
import exception.MobileBankException;
import model.Credentials;
import model.User;

import java.sql.SQLException;
import java.util.UUID;

import static db.JdbcService.*;
import static exception.DbErrorCode.*;

public class UserDao {

    public String loginUserByLogin(Credentials credentials) throws MobileBankException, SQLException {
        User user = getUserByLogin(credentials.getLogin());
        if (user == null || !credentials.getPassword().equals(user.getPassword())) {
            throw new MobileBankException(USER_NOT_FOUND);
        }
        if (getTokenByUserId(user.getId()) != null) {
            throw new MobileBankException(USER_IS_AUTHORIZED);
        }
        String token = UUID.randomUUID().toString();
        insertUserToAuthorized(user.getId(), token);
        return token;
    }

    public String loginUserByPhone(Credentials credentials) throws MobileBankException, SQLException {
        User user = JdbcService.getUserByLogin(credentials.getLogin());
        if (user == null
                || !credentials.getPassword().equals(user.getPassword())) {
            throw new MobileBankException(USER_NOT_FOUND.toString());
        }
        if (getTokenByUserId(user.getId()) != null) {
            throw new MobileBankException(USER_IS_AUTHORIZED);
        }
        String token = UUID.randomUUID().toString();
        insertUserToAuthorized(user.getId(), token);
        return token;
    }

    public User getUserByLogin(String login) throws SQLException, MobileBankException {
        return JdbcService.getUserByLogin(login);
    }

    public User getUserByPhone(String phone) throws SQLException, MobileBankException {
        return JdbcService.getUserByPhone(phone);
    }

    public void signInUser(User user) throws SQLException, MobileBankException  {
        if (user == null) {
            throw new MobileBankException(USER_IS_REQUIRED);
        }
        User userInDb = getUserByLogin(user.getLogin());
        if (userInDb != null) {
            throw new MobileBankException(USER_EXISTS.toString());
        }
        if (getTokenByUserId(user.getId()) != null) {
            throw new MobileBankException(USER_IS_AUTHORIZED);
        }
        insertUser(user);
    }

    public void logout(String token) throws SQLException, MobileBankException  {
        if (JdbcService.getUserIdByToken(token) == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        JdbcService.deleteFromAuthorized(token);
    }
}
