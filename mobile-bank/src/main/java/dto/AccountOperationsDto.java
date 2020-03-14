package dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class AccountOperationsDto implements Serializable {

    private String token;
    private String accountIdFrom;
    private String accountIdTo;
    private BigDecimal amount;
    private String accCode;
    private String phone;

    public AccountOperationsDto() {}

    public AccountOperationsDto(String token, String accCode, BigDecimal amount) {
        this.token = token;
        this.accCode = accCode;
        this.amount = amount;
    }

    public AccountOperationsDto(String token, String phone, String accCode, BigDecimal amount) {
        this.token = token;
        this.phone = phone;
        this.accCode = accCode;
        this.amount = amount;
    }

    public AccountOperationsDto(String token, String accountIdTo, BigDecimal amount, String accCode) {
        this(token, amount, accCode, null, accountIdTo);
    }

    public AccountOperationsDto(String token, BigDecimal amount, String accCode,
                                String accountIdFrom, String accountIdTo) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
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

    public String getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(String accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public String getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(String accountIdTo) {
        this.accountIdTo = accountIdTo;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOperationsDto that = (AccountOperationsDto) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(accountIdFrom, that.accountIdFrom) &&
                Objects.equals(accountIdTo, that.accountIdTo) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(accCode, that.accCode) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, accountIdFrom, accountIdTo, amount, accCode, phone);
    }
}
