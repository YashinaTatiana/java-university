package dao;

import db.*;
import dto.AccountOperationsDto;
import exception.MobileBankException;
import model.AccCode;
import model.Account;
import model.Operation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static db.AccountRepository.*;
import static db.OperationRepository.insertOperation;
import static db.UserRepository.getUserIdByToken;
import static exception.DbErrorCode.*;
import static exception.MobileBankErrorCode.*;
import static utils.CurrencyConverter.convert;

public class OperationsDao {

    public void refillAccount(AccountOperationsDto accountDto)
            throws MobileBankException, SQLException {
        String userToken = accountDto.getToken();
        String accountId = accountDto.getAccountIdTo();
        BigDecimal addedAmount = accountDto.getAmount();
        AccCode accCode = AccCode.valueOf(accountDto.getAccCode().toUpperCase());
        if (userToken == null || getUserIdByToken(userToken) == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new MobileBankException(ACCOUNT_IS_REQUIRED);
        }
        Account account = getAccountById(accountId);
        if (account == null) {
            throw new MobileBankException(ACCOUNT_NOT_FOUND);
        }
        if (addedAmount == null || addedAmount.longValue() < 0) {
            throw new MobileBankException(AMOUNT_NOT_VALID);
        }
        BigDecimal amountAfter =  account.getAmount().add(convert(accCode, account.getAccCode(), addedAmount));
        updateAmountById(accountId, amountAfter);
    }

    public void transaction(AccountOperationsDto accountDto)
            throws MobileBankException, SQLException {
        String userToken = accountDto.getToken();
        String accountIdFrom = accountDto.getAccountIdFrom();
        String accountIdTo = accountDto.getAccountIdTo();
        BigDecimal addedAmount = accountDto.getAmount();
        AccCode accCode = AccCode.valueOf(accountDto.getAccCode());

        if (userToken == null || getUserIdByToken(userToken) == null) {
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
        Account accountFrom = getAccountById(accountIdFrom);
        Account accountTo = getAccountById(accountIdTo);
        if (accountFrom == null) {
            throw new MobileBankException(ACCOUNT_FROM_NOT_FOUND);
        }
        if (accountTo == null) {
            throw new MobileBankException(ACCOUNT_TO_NOT_FOUND);
        }
        BigDecimal amountTo = accountTo.getAmount();
        BigDecimal amountFrom = accountFrom.getAmount();
        BigDecimal equivalentFrom = convert(accCode, accountFrom.getAccCode(), addedAmount);
        BigDecimal equivalentTo = convert(accCode, accountTo.getAccCode(), addedAmount);
        if (accountFrom.getAmount().compareTo(equivalentFrom) < 0) {
            throw new MobileBankException(AMOUNT_NOT_ENOUGH);
        }
        updateAmountById(accountIdTo, amountTo.add(equivalentTo));
        updateAmountById(accountIdFrom,amountFrom.subtract(equivalentFrom));

        insertOperation(new Operation(Instant.now().toString(),
                accCode.toString(), accountIdFrom, accountIdTo,
                addedAmount, amountFrom, amountFrom.subtract(equivalentFrom)));

        insertOperation(new Operation(Instant.now().toString(),
                accCode.toString(), accountIdFrom, accountIdTo,
                addedAmount, amountTo, amountTo.add(equivalentTo)));
    }

    public List<Operation> getOperations(String token) throws MobileBankException, SQLException {
        Long userId = getUserIdByToken(token);
        if (userId == null) {
            throw new MobileBankException(USER_IS_NOT_AUTHORIZED);
        }
        return OperationRepository.getOperations(userId);
    }
}
