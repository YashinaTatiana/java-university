package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAccountDto implements Serializable {

    private String token;
    private BigDecimal amount;
    private String accCode;

    public CreateAccountDto(String token, String accCode) {
        this(token, BigDecimal.ZERO, accCode);
    }
}
