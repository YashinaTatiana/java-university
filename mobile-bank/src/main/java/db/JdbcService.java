package db;

import exception.MobileBankException;
import model.AccCode;
import model.Account;
import model.Operation;
import model.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static utils.Constants.*;


public class JdbcService {

    private static final String INSERT_USER = "INSERT INTO users VALUES (null, ?, ?, ?, ?)";
    private static final String GET_USER_BY_PHONE = "SELECT * FROM users WHERE phone = \"%s\"";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = \"%s\"";

    private static final String GET_USER_FROM_AUTHORIZED = "SELECT token FROM authorized WHERE user_id = %s";
    private static final String INSERT_USER_TO_AUTHORIZED = "INSERT INTO authorized VALUES (?, ?)";
    private static final String GET_USER_ID_FROM_AUTHORIZED = "SELECT user_id FROM authorized WHERE token = \"%s\"";
    private static final String DELETE_USER_FROM_AUTHORIZED = "DELETE FROM authorized WHERE token =\"%s\"";

    private static final String INSERT_ACCOUNT = "INSERT INTO accounts VALUES (?, ?, ?, ?)";
    private static final String GET_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id = \"%s\"";
    private static final String GET_ACCOUNTS_BY_CLIENT_ID = "SELECT * FROM accounts WHERE client_id = %s";

    private static final String INSERT_OPERATION = "INSERT INTO operations VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_AMOUNT_BY_ID = "UPDATE accounts SET amount = %s WHERE id = \"%s\"";
    private static final String SELECT_OPERATIONS = "SELECT * FROM operations WHERE account_from in " +
            "( SELECT id FROM accounts WHERE client_id = %s)";

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

    // Accounts
    public static void insertAccount(Account account) throws SQLException {
        try (PreparedStatement statement =
                     JdbcUtils.getConnection().prepareStatement(INSERT_ACCOUNT)) {
            statement.setString(1, account.getId().toString());
            statement.setLong(2, account.getClientId());
            statement.setBigDecimal(3, account.getAmount());
            statement.setString(4, account.getAccCode().toString());
            statement.executeUpdate();
        }
    }

    public static Account getAccountById(String id) throws SQLException, MobileBankException {
        String query = format(GET_ACCOUNT_BY_ID, id);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            if (rs.next()){
                return new Account(rs.getString(ID),
                        rs.getLong(CLIENT_ID),
                        rs.getBigDecimal(AMOUNT),
                        AccCode.valueOf(rs.getString(ACC_CODE)));
            }
        }
        return null;
    }

    public static List<Account> getAccountsByUserId(long id) throws SQLException, MobileBankException {
        String query = format(GET_ACCOUNTS_BY_CLIENT_ID, id);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
             List<Account> accounts = new ArrayList<>();
             while (rs.next()){
                accounts.add(new Account(rs.getString(ID),
                        rs.getLong(CLIENT_ID),
                        rs.getBigDecimal(AMOUNT),
                        AccCode.valueOf(rs.getString(ACC_CODE))));
             }
             return accounts;
        }
    }

    // Operations
    public static void insertOperation(Operation operation) throws SQLException {
        try (PreparedStatement statement =
                     JdbcUtils.getConnection().prepareStatement(INSERT_OPERATION, RETURN_GENERATED_KEYS)) {
            statement.setString(1, operation.getDate());
            statement.setString(2, operation.getAccCode().toString());
            statement.setString(3, operation.getAccountFrom().toString());
            statement.setString(4, operation.getAccountTo().toString());
            statement.setBigDecimal(5, operation.getAmount());
            statement.setBigDecimal(6, operation.getAmountBefore());
            statement.setBigDecimal(7, operation.getAmountAfter());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                operation.setId(rs.getInt(1));
            }
            rs.close();
        }
    }

    public static List<Operation> getOperations(long id) throws SQLException, MobileBankException{
        String query = format(SELECT_OPERATIONS, id);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery(query)){
            List<Operation> operations = new ArrayList<>();
            while (rs.next()) {
                Operation operation = new Operation(rs.getLong(ID),
                        rs.getString(DATE),
                        rs.getString(ACC_CODE),
                        rs.getString(ACCOUNT_FROM),
                        rs.getString(ACCOUNT_TO),
                        rs.getBigDecimal(AMOUNT),
                        rs.getBigDecimal(AMOUNT_BEFORE),
                        rs.getBigDecimal(AMOUNT_AFTER));
                operations.add(operation);
            }
            return operations;
        }
    }

    public static void updateAmountById(String accountId, BigDecimal amount) throws SQLException {
        String query = String.format(UPDATE_AMOUNT_BY_ID, amount, accountId);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
}
