package com.university.accounts.dto;

import com.university.accounts.entity.AccCode;
import com.university.accounts.stereotypes.AllowedCurrency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperationDto {

    @NotNull
    private long id;

    @NotEmpty
    private String date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accCode")
    @AllowedCurrency
    private AccCode accCode;

    @NotNull
    private long senderId;

    @NotNull
    private long recipientId;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @Min(0)
    private BigDecimal amountBefore;

    @NotNull
    @Min(0)
    private BigDecimal amountAfter;

    public OperationDto(@NonNull String date,
                     @NonNull @AllowedCurrency AccCode accCode,
                     @NonNull long senderId,
                     @NonNull long recipientId,
                     @NonNull BigDecimal amount,
                     @NonNull BigDecimal amountBefore,
                     @NonNull BigDecimal amountAfter) {
        this.date = date;
        this.accCode = accCode;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }
}

