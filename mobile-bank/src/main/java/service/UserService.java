package service;

import dao.UserDao;;
import dto.Response;
import exception.MobileBankException;
import model.Credentials;
import model.User;

import java.sql.SQLException;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Response<String> signUp(User user) {
        try {
            if (null == user) {
                throw new MobileBankException("User is not defined!");
            }
            else if (userDao.getUserByLogin(user.getLogin()) != null) {
                throw new MobileBankException("User with such login is exists!");
            } else if (userDao.getUserByPhone(user.getPhone()) != null) {
                throw new MobileBankException("User with such phone is exists!");
            }
            userDao.signInUser(user);
            return new Response<>("User signed up", "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }

    public Response<String> loginUserByLogin(Credentials credentials) {
        try {
            if (null == credentials) {
                throw new MobileBankException("Credentials is not provided!");
            }
            String token = userDao.loginUserByLogin(credentials);
            return new Response<>(token, "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }

    public Response<String> loginUserByPhone(Credentials credentials) {
        try {
            if (null == credentials) {
                throw new MobileBankException("Credentials is not provided!");
            }
            String token = userDao.loginUserByPhone(credentials);
            return new Response<>(token, "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }

    public Response<String> logoutUser(String token) {
        try {
            userDao.logout(token);
            return new Response<>("Logout...", "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }
}
