package service;

import dao.OperationsDao;
import dto.AccountOperationsDto;
import dto.Response;
import exception.MobileBankException;
import model.Operation;

import java.sql.SQLException;
import java.util.List;

public class OperationService {

    private OperationsDao operationsDao;

    public OperationService(OperationsDao operationsDao) {
        this.operationsDao = operationsDao;
    }

    public Response<String> refillAccount(AccountOperationsDto accountDto) {
        try {
            operationsDao.refillAccount(accountDto);
            return new Response<>("Account refilled successfully", "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());

        }
    }

    public Response<String> moneyTransferByPhone(AccountOperationsDto accountDto) {
        try {
            operationsDao.transaction(accountDto);
            return new Response<>("Operation completed successfully", "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }

    public Response<List<Operation>> getOperationsInfo(String token) {
        try {
            return new Response<>(operationsDao.getOperations(token), "");
        } catch (MobileBankException | SQLException ex) {
            return new Response<>(null, ex.getMessage());
        }
    }
}
