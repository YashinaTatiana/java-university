package dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AccountOperationsDto implements Serializable {

    private String token;
    private String accountIdFrom;
    private String accountIdTo;
    private BigDecimal amount;
    private String accCode;
    private String phone;

    public AccountOperationsDto(String token, String accountIdTo, BigDecimal amount, String accCode) {
        this(token, amount, accCode, null, accountIdTo);
    }

    public AccountOperationsDto(String token, BigDecimal amount, String accCode,
                                String accountIdFrom, String accountIdTo) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.token = token;
        this.amount = amount;
        this.accCode = accCode.toUpperCase();
    }
}
