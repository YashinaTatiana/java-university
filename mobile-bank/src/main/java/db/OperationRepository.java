package db;

import exception.MobileBankException;
import model.Operation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static utils.Constants.*;


public class OperationRepository {

    private static final String INSERT_OPERATION = "INSERT INTO operations VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_OPERATIONS = "SELECT * FROM operations WHERE account_from in " +
            "( SELECT id FROM accounts WHERE client_id = %s)";

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
}
