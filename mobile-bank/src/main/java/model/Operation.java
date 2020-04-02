package model;

import exception.MobileBankException;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import static exception.MobileBankErrorCode.INVALID_CURRENCY;

@NoArgsConstructor
@Setter
public class Operation implements Serializable {

    private long id;
    private String date;
    private AccCode accCode;
    private UUID accountFrom;
    private UUID getAccountTo;
    private BigDecimal amount;
    private BigDecimal amountBefore;
    private BigDecimal amountAfter;

    public Operation(String date, String accCode,
                     String accountFrom, String getAccountTo, BigDecimal amount,
                     BigDecimal amountBefore, BigDecimal amountAfter) throws MobileBankException {
        this.id = id;
        this.date = date;
        setAccCode(accCode);
        this.accountFrom = UUID.fromString(accountFrom);
        this.getAccountTo = UUID.fromString(getAccountTo);
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public Operation(long id, String date, String accCode,
                     String accountFrom, String getAccountTo, BigDecimal amount,
                     BigDecimal amountBefore, BigDecimal amountAfter) throws MobileBankException {
        this.id = id;
        this.date = date;
        setAccCode(accCode);
        this.accountFrom = UUID.fromString(accountFrom);
        this.getAccountTo = UUID.fromString(getAccountTo);
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public AccCode getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) throws MobileBankException {
        try {
            this.accCode = AccCode.valueOf(accCode.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MobileBankException(INVALID_CURRENCY);
        }
    }

    public UUID getAccountFrom() {
        return accountFrom;
    }

    public UUID getAccountTo() {
        return getAccountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountBefore() {
        return amountBefore;
    }

    public BigDecimal getAmountAfter() {
        return amountAfter;
    }

    @Override
    public String toString() {
        return "Operation #" + id +
                "\nDate: " + date + '\'' +
                "\nCurrency: " + accCode +
                "\nFrom: " + accountFrom +
                "\nTo: " + getAccountTo +
                "\nAmount: " + amount +
                "\nBefore transaction: " + amountBefore +
                "\nAfter transaction: " + amountAfter +
                "\n-------------------------";
    }
}
