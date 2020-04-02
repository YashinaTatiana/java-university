package com.university.accounts.controller;

import com.university.accounts.dto.RefillAccountOperationDto;
import com.university.accounts.dto.TransferMoneyOperationDto;
import com.university.accounts.entity.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Operation controller")
@RequestMapping("/v1/operation")
public interface OperationController {

    @GetMapping("/info/{userId}")
    @ApiOperation(value = "Get user operations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully complete"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public ResponseEntity<List<Operation>> getOperations(@PathVariable("userId") Long userId);

    @PostMapping("/refill")
    @ApiOperation(value = "Refill account operation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation is completed successfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public void refillAccount(@RequestBody @Valid RefillAccountOperationDto operationDto);

    @PostMapping("/transfer")
    @ApiOperation(value = "Transfer money")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation is completed successfully"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Invalid request")
    })
    public void transferMoneyByPhone(@RequestBody @Valid TransferMoneyOperationDto operationDto);
}
