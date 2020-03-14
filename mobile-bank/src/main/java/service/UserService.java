package service;

import dao.UserDao;;
import dto.UserResponseDto;
import exception.MobileBankException;
import model.Credentials;
import model.User;

import java.sql.SQLException;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserResponseDto signInUser(User user) {
        UserResponseDto responseDto = new UserResponseDto();
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
            return responseDto;
        } catch (MobileBankException | SQLException ex) {
            responseDto.setError(ex.getMessage());
            return responseDto;
        }
    }

    public UserResponseDto loginUserByLogin(Credentials credentials) {
        UserResponseDto responseDto = new UserResponseDto();
        try {
            if (null == credentials) {
                throw new MobileBankException("Credentials is not provided!");
            }
            responseDto.setResponse(userDao.loginUserByLogin(credentials));
            return responseDto;
        } catch (MobileBankException | SQLException ex) {
            responseDto.setError(ex.getMessage());
            return responseDto;
        }
    }

    public UserResponseDto loginUserByPhone(Credentials credentials) {
        UserResponseDto responseDto = new UserResponseDto();
        try {
            if (null == credentials) {
                throw new MobileBankException("Credentials is not provided!");
            }
            responseDto.setResponse(userDao.loginUserByPhone(credentials));
            return responseDto;
        } catch (MobileBankException | SQLException ex) {
            responseDto.setError(ex.getMessage());
            return responseDto;
        }
    }

    public UserResponseDto logoutUser(String token) {
        UserResponseDto dto = new UserResponseDto();
        try {
            userDao.logout(token);
            return dto;
        } catch (MobileBankException | SQLException ex) {
            dto.setError(ex.getMessage());
            return dto;
        }
    }
}
