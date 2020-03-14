package dao;

import db.JdbcService;
import dto.AccountOperationsDto;
import exception.MobileBankException;
import model.AccCode;
import model.Account;
import model.Operation;
import utils.CurrencyConverter;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import static exception.DbErrorCode.*;
import static exception.MobileBankErrorCode.*;

public class OperationsDao {

    public void refillAccount(AccountOperationsDto accountDto)
            throws MobileBankException, SQLException {
        String userToken = accountDto.getToken();
        String accountId = accountDto.getAccountIdTo();
        BigDecimal addedAmount = accountDto.getAmount();
        AccCode accCode = AccCode.valueOf(accountDto.getAccCode());
        if (userToken == null || JdbcService.getUserIdByToken(userToken) == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new MobileBankException(ACCOUNT_IS_REQUIRED);
        }
        Account account = JdbcService.getAccountById(accountId);
        if (account == null) {
            throw new MobileBankException(ACCOUNT_NOT_FOUND);
        }
        if (addedAmount == null || addedAmount.longValue() < 0) {
            throw new MobileBankException(AMOUNT_NOT_VALID);
        }
        JdbcService.updateAmountById(accountId,
                account.getAmount().add(CurrencyConverter.convert(accCode, account.getAccCode(), addedAmount)));
    }

    public void transaction(AccountOperationsDto accountDto)
            throws MobileBankException, SQLException {
        String userToken = accountDto.getToken();
        String accountIdFrom = accountDto.getAccountIdFrom();
        String accountIdTo = accountDto.getAccountIdTo();
        BigDecimal addedAmount = accountDto.getAmount();
        AccCode accCode = AccCode.valueOf(accountDto.getAccCode());

        if (userToken == null || JdbcService.getUserIdByToken(userToken) == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        if (accountIdFrom == null || accountIdFrom.trim().isEmpty()) {
            throw new MobileBankException(ACCOUNT_ID_IS_REQUIRED);
        }
        if (accountIdTo == null || accountIdTo.trim().isEmpty()) {
            throw new MobileBankException(ACCOUNT_ID_IS_REQUIRED);
        }
        if (addedAmount == null || addedAmount.longValue() < 0) {
            throw new MobileBankException(AMOUNT_IS_INVALID);
        }
        Account accountFrom = JdbcService.getAccountById(accountIdFrom);
        Account accountTo = JdbcService.getAccountById(accountIdTo);
        if (accountFrom == null) {
            throw new MobileBankException(ACCOUNT_FROM_NOT_FOUND);
        }
        if (accountTo == null) {
            throw new MobileBankException(ACCOUNT_TO_NOT_FOUND);
        }
        BigDecimal amountTo = accountTo.getAmount();
        BigDecimal amountFrom = accountFrom.getAmount();
        BigDecimal equivalentFrom = CurrencyConverter.convert(accCode, accountFrom.getAccCode(), addedAmount);
        BigDecimal equivalentTo = CurrencyConverter.convert(accCode, accountTo.getAccCode(), addedAmount);
        if (accountFrom.getAmount().compareTo(equivalentFrom) < 0) {
            throw new MobileBankException(AMOUNT_NOT_ENOUGH);
        }

        JdbcService.updateAmountById(accountIdTo,
                amountTo.add(equivalentTo));
        JdbcService.updateAmountById(accountIdFrom,
                amountFrom.subtract(equivalentFrom));

        JdbcService.insertOperation(new Operation(Calendar.getInstance().getTime().toString(),
                accCode.toString(), accountIdFrom, accountIdTo,
                addedAmount, amountFrom, amountFrom.subtract(equivalentFrom)));

        JdbcService.insertOperation(new Operation(Calendar.getInstance().getTime().toString(),
                accCode.toString(), accountIdFrom, accountIdTo,
                addedAmount, amountTo, amountTo.add(equivalentTo)));
    }

    public List<Operation> getOperations(String token) throws MobileBankException, SQLException {
        Long userId = JdbcService.getUserIdByToken(token);
        if (userId == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        return JdbcService.getOperations(userId);
    }
}
