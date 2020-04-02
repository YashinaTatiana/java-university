package db;

import exception.MobileBankException;
import model.AccCode;
import model.Account;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static utils.Constants.*;


public class AccountRepository {

    private static final String INSERT_ACCOUNT = "INSERT INTO accounts VALUES (?, ?, ?, ?)";
    private static final String GET_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE id = \"%s\"";
    private static final String GET_ACCOUNTS_BY_CLIENT_ID = "SELECT * FROM accounts WHERE client_id = %s";
    private static final String UPDATE_AMOUNT_BY_ID = "UPDATE accounts SET amount = %s WHERE id = \"%s\"";

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

    public static void updateAmountById(String accountId, BigDecimal amount) throws SQLException {
        String query = String.format(UPDATE_AMOUNT_BY_ID, amount, accountId);
        try (PreparedStatement statement = JdbcUtils.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
}
