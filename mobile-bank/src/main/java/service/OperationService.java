package service;

import dao.OperationsDao;
import dto.AccountOperationsDto;
import dto.OperationsResponseDto;
import exception.MobileBankException;

import java.sql.SQLException;

public class OperationService {

    private OperationsDao operationsDao;

    public OperationService(OperationsDao operationsDao) {
        this.operationsDao = operationsDao;
    }

    public OperationsResponseDto refillAccount(AccountOperationsDto accountDto) {
        OperationsResponseDto operationsResponseDto = new OperationsResponseDto();
        try {
            operationsDao.refillAccount(accountDto);
            return operationsResponseDto;
        } catch (MobileBankException | SQLException ex) {
            operationsResponseDto.setError(ex.getMessage());
            return operationsResponseDto;
        }
    }

    public OperationsResponseDto moneyTransferByPhone(AccountOperationsDto accountDto) {
        OperationsResponseDto dto = new OperationsResponseDto();
        try {
            operationsDao.transaction(accountDto);
            return dto;
        } catch (MobileBankException | SQLException ex) {
            dto.setError(ex.getMessage());
            return dto;
        }
    }

    public OperationsResponseDto getOperationsInfo(String token) {
        OperationsResponseDto dto = new OperationsResponseDto();
        try {
            dto.setOperations(operationsDao.getOperations(token));
            return dto;
        } catch (MobileBankException | SQLException ex) {
            dto.setError(ex.getMessage());
            return dto;
        }
    }
}
