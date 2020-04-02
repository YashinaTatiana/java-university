package db;

import exception.MobileBankException;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static utils.Constants.*;


public class UserRepository {

    private static final String INSERT_USER = "INSERT INTO users VALUES (null, ?, ?, ?, ?)";
    private static final String GET_USER_BY_PHONE = "SELECT * FROM users WHERE phone = \"%s\"";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = \"%s\"";

    private static final String GET_USER_FROM_AUTHORIZED = "SELECT token FROM authorized WHERE user_id = %s";
    private static final String INSERT_USER_TO_AUTHORIZED = "INSERT INTO authorized VALUES (?, ?)";
    private static final String GET_USER_ID_FROM_AUTHORIZED = "SELECT user_id FROM authorized WHERE token = \"%s\"";
    private static final String DELETE_USER_FROM_AUTHORIZED = "DELETE FROM authorized WHERE token =\"%s\"";

    // User
    public static void insertUser(User user) throws SQLException {
        try (PreparedStatement statement =
                     JdbcUtils.getConnection().prepareStatement(INSERT_USER, RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getPhone());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                user.setId(rs.getInt(1));
            }
            rs.close();
        }
    }

    public static User getUserByPhone(String phone) throws SQLException, MobileBankException {
        String query = format(GET_USER_BY_PHONE, phone);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return new User(rs.getLong(ID),
                        rs.getString(LOGIN),
                        rs.getString(PASSWORD),
                        rs.getString(ADDRESS),
                        rs.getString(PHONE));
            }
        }
        return null;
    }

    public static User getUserByLogin(String login) throws SQLException, MobileBankException {
        String query = format(GET_USER_BY_LOGIN, login);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return new User(rs.getLong(ID),
                        rs.getString(LOGIN),
                        rs.getString(PASSWORD),
                        rs.getString(ADDRESS),
                        rs.getString(PHONE));
            }
        }
        return null;
    }

    // Authorized
    public static void insertUserToAuthorized(long id, String token) throws SQLException {
        String query = format(INSERT_USER_TO_AUTHORIZED, id, token);
        try (PreparedStatement statement =
                     JdbcUtils.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.setString(2, token);
            statement.executeUpdate();
        }
    }

    public static void deleteFromAuthorized(String token) throws SQLException {
        String query = format(DELETE_USER_FROM_AUTHORIZED, token);
        try (PreparedStatement statement =
                     JdbcUtils.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    public static Long getUserIdByToken(String token) throws SQLException, MobileBankException {
        String query = format(GET_USER_ID_FROM_AUTHORIZED, token);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(USER_ID);
            }
        }
        return null;
    }

    public static String getTokenByUserId(long id) throws SQLException {
        String query = format(GET_USER_FROM_AUTHORIZED, id);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()){
                return rs.getString(TOKEN);
            }
        }
        return null;
    }
}
