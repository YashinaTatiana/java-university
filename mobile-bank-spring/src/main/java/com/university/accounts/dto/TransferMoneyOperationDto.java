package com.university.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.university.accounts.entity.AccCode;
import com.university.accounts.stereotypes.AllowedCurrency;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferMoneyOperationDto {

    @NotNull
    @JsonProperty("senderAccountId")
    private Long senderAccountId;

    @NotEmpty
    @JsonProperty("phone")
    private String phone;

    @NotNull
    @Min(0)
    @JsonProperty("amount")
    private BigDecimal amount;

    @AllowedCurrency
    @Enumerated(EnumType.STRING)
    @JsonProperty("accCode")
    private AccCode accCode;
}
