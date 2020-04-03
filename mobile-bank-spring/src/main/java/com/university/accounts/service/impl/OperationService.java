package com.university.accounts.service.impl;

import com.university.accounts.dao.AccountRepository;
import com.university.accounts.dao.OperationRepository;
import com.university.accounts.dto.RefillAccountOperationDto;
import com.university.accounts.dto.TransferMoneyOperationDto;
import com.university.accounts.entity.*;
import com.university.accounts.service.security.AuthenticationService;
import com.university.accounts.exceptions.BadRequest;
import com.university.accounts.exceptions.ErrorMessage;
import com.university.accounts.exceptions.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.university.accounts.entity.OperationType.*;
import static com.university.accounts.utils.CurrencyConverterUtils.convert;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationService {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private final OperationRepository operationRepository;

    @Autowired
    private final AuthenticationService authenticationService;

    public List<Operation> getOperations(Long id) throws ValidationException {
        User user = authenticationService.getAuthenticatedUser();
        if (!user.getId().equals(id)) {
            throw new NotAuthorizedException(ErrorMessage.USER_NOT_AUTHORIZED.getMessage());
        }
        return operationRepository.getUserOperations(user.getAccountList().stream()
                .map(Account::getId).collect(toList()));
    }

    @Transactional
    public void refillAccount(RefillAccountOperationDto operationDto)
            throws ValidationException {
        Long accountId = operationDto.getAccountId();
        AccCode transAccCode = operationDto.getAccCode();
        Account account = getCurrentUserAccount(accountId);
        BigDecimal transAmount = operationDto.getAmount();
        BigDecimal amountBefore = account.getAmount();
        BigDecimal amountAfter = amountBefore.add(convert(transAccCode, account.getCurrency(), transAmount));

        accountRepository.setAccountAmount(operationDto.getAccountId(), amountAfter);
        operationRepository.save(new Operation(Instant.now().toString(),
                transAccCode, accountId, transAmount, amountBefore, amountAfter, REFILL));
    }

    @Transactional
    public void transferMoney(TransferMoneyOperationDto operationDto) throws ValidationException {
        String date = Instant.now().toString();

        Account senderAccount = getCurrentUserAccount(operationDto.getSenderAccountId());
        Account recipientAccount = accountService.getDefaultByUserPhone(operationDto.getPhone());

        BigDecimal transAmount = operationDto.getAmount();
        AccCode transAccCode = operationDto.getAccCode();

        AccCode recipientAccCode = recipientAccount.getCurrency();
        AccCode senderAccCode = senderAccount.getCurrency();

        BigDecimal amountBefore = senderAccount.getAmount();
        BigDecimal amountAfter = amountBefore.subtract(convert(transAccCode, senderAccCode, transAmount));

        if (amountAfter.compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequest("Not enough money");
        }

        accountRepository.setAccountAmount(senderAccount.getId(), amountAfter);
        Operation operationReceipts = new Operation(date, transAccCode, senderAccount.getId(),
                recipientAccount.getId(), transAmount, amountBefore, amountAfter, RECEIPTS);
        operationRepository.save(operationReceipts);

        amountBefore = recipientAccount.getAmount();
        amountAfter = amountBefore.add(convert(transAccCode, recipientAccCode, transAmount));

        accountRepository.setAccountAmount(recipientAccount.getId(), amountAfter);

        Operation operationTransfer = new Operation(date,
                transAccCode, senderAccount.getId(), recipientAccount.getId(),
                transAmount, amountBefore, amountAfter, TRANSFER);
        operationRepository.save(operationTransfer);
    }

    private Account getCurrentUserAccount(Long accountId) {
        User user = authenticationService.getAuthenticatedUser();
        List<Account> userAccounts =  user.getAccountList();
        return userAccounts.stream()
                .filter(a -> a.getId() == accountId)
                .findFirst()
                .orElseThrow(() -> new BadRequest(ErrorMessage.INVALID_ACCOUNT.getMessage()));
    }
}

