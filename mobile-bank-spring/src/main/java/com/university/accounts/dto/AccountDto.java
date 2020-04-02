package com.university.accounts.dto;

import com.university.accounts.entity.AccCode;
import com.university.accounts.stereotypes.AllowedCurrency;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @NotNull
    Long id;

    @NotNull
    Long clientId;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @AllowedCurrency
    @Enumerated(EnumType.STRING)
    private AccCode currency;

    private Boolean isDefault;
}
