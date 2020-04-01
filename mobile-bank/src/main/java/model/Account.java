package model;

import exception.MobileBankException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static exception.MobileBankErrorCode.INVALID_CURRENCY;
import static java.util.UUID.randomUUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account implements Serializable {

    private UUID id;
    private long clientId;
    private BigDecimal amount;
    private AccCode accCode;

    public Account(AccCode accCode) throws MobileBankException {
        this(BigDecimal.valueOf(0), accCode);
    }

    public Account(BigDecimal amount, String accCode) throws MobileBankException {
        setAmount(amount);
        setAccCode(accCode);
        this.id = randomUUID();
    }

    public Account(BigDecimal amount, AccCode accCode) throws MobileBankException {
        setAmount(amount);
        setAccCode(accCode);
        this.id = randomUUID();
    }

    public Account(String id, long clientId, BigDecimal amount, AccCode accCode)
            throws MobileBankException {
        setId(UUID.fromString(id));
        setClientId(clientId);
        setAmount(amount);
        setAccCode(accCode);
    }

    public void setId(UUID id) throws MobileBankException {
        if (null == id || id.toString().trim().isEmpty()) {
            throw new MobileBankException("Id is invalid!");
        }
        this.id = id;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setAmount(BigDecimal amount) throws MobileBankException {
        if (null == amount || amount.longValue() < 0) {
            throw new MobileBankException("Amount is invalid!");
        }
        this.amount = amount;
    }

    public void setAccCode(AccCode accCode) {
        this.accCode = accCode;
    }

    public void setAccCode(String accCode) throws MobileBankException {
        try {
            this.accCode = AccCode.valueOf(accCode.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MobileBankException(INVALID_CURRENCY);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return clientId == account.clientId &&
                Objects.equals(id, account.id) &&
                Objects.equals(amount, account.amount) &&
                accCode == account.accCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, amount, accCode);
    }
}
