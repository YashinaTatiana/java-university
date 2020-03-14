package dto;

import model.AccCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CreateAccountDto implements Serializable {

    private String token;
    private BigDecimal amount;
    private String accCode;

    public CreateAccountDto(){}

    public CreateAccountDto(String token, String accCode) {
        this(token, BigDecimal.valueOf(0), accCode);
    }

    public CreateAccountDto(String token, BigDecimal amount, String accCode) {
        this.token = token;
        this.amount = amount;
        this.accCode = accCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountDto that = (CreateAccountDto) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(amount, that.amount) &&
                accCode == that.accCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, amount, accCode);
    }
}
