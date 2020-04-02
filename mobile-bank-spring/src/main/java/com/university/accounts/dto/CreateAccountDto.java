package com.university.accounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.university.accounts.entity.AccCode;
import com.university.accounts.stereotypes.AllowedCurrency;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Data
public class CreateAccountDto {

    @NotNull
    @Min(0)
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotNull
    @AllowedCurrency
    @Enumerated(EnumType.STRING)
    @JsonProperty("accCode")
    private AccCode accCode;

    public CreateAccountDto(AccCode accCode) {
        this(BigDecimal.valueOf(0), accCode);
    }
}
