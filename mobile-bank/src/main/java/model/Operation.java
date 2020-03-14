package model;

import exception.MobileBankException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static exception.MobileBankErrorCode.INVALID_CURRENCY;

public class Operation implements Serializable {

    private long id;
    private String date;
    private AccCode accCode;
    private UUID accountFrom;
    private UUID getAccountTo;
    private BigDecimal amount;
    private BigDecimal amountBefore;
    private BigDecimal amountAfter;

    public Operation(){}

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

    public Operation(long id, String date, AccCode accCode,
                     UUID accountFrom, UUID getAccountTo, BigDecimal amount,
                     BigDecimal amountBefore, BigDecimal amountAfter) {
        this.id = id;
        this.date = date;
        this.accCode = accCode;
        this.accountFrom = accountFrom;
        this.getAccountTo = getAccountTo;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AccCode getAccCode() {
        return accCode;
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

    public UUID getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(UUID accountFrom) {
        this.accountFrom = accountFrom;
    }

    public UUID getAccountTo() {
        return getAccountTo;
    }

    public void setAccountTo(UUID getAccountTo) {
        this.getAccountTo = getAccountTo;
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

    public void setAmountBefore(BigDecimal amountBefore)  {
        this.amountBefore = amountBefore;
    }

    public BigDecimal getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(BigDecimal amountAfter) {
        this.amountAfter = amountAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return id == operation.id &&
                Objects.equals(date, operation.date) &&
                accCode == operation.accCode &&
                Objects.equals(accountFrom, operation.accountFrom) &&
                Objects.equals(getAccountTo, operation.getAccountTo) &&
                Objects.equals(amount, operation.amount) &&
                Objects.equals(amountBefore, operation.amountBefore) &&
                Objects.equals(amountAfter, operation.amountAfter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, accCode, accountFrom, getAccountTo, amount, amountBefore, amountAfter);
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
