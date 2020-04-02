package com.university.accounts.controller.impl;

import com.university.accounts.controller.OperationController;
import com.university.accounts.dto.RefillAccountOperationDto;
import com.university.accounts.dto.TransferMoneyOperationDto;
import com.university.accounts.entity.Operation;
import com.university.accounts.service.impl.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperationControllerImpl implements OperationController {

    @Autowired
    private OperationService operationService;

    @Override
    public ResponseEntity<List<Operation>> getOperations(Long userId) {
        return ResponseEntity.ok(operationService.getOperations(userId));
    }

    @Override
    public void refillAccount(RefillAccountOperationDto operationDto) {
        operationService.refillAccount(operationDto);
    }

    @Override
    public void transferMoneyByPhone(TransferMoneyOperationDto operationDto) {
        operationService.transferMoney(operationDto);
    }
}
