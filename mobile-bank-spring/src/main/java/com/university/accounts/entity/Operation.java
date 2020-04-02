package com.university.accounts.entity;

import com.university.accounts.stereotypes.AllowedCurrency;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "date")
    private String date;

    @Enumerated(EnumType.STRING)
    @Column(name = "accCode", nullable = false)
    @AllowedCurrency
    private AccCode accCode;

    @Column(name = "senderId")
    private long senderId;

    @Column(name = "recipientId", nullable = false)
    private long recipientId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "amountBefore", nullable = false)
    private BigDecimal amountBefore;

    @Column(name = "amountAfter", nullable = false)
    private BigDecimal amountAfter;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationType type;

    public Operation(String date,
                     @AllowedCurrency AccCode accCode,
                     long senderId,
                     long recipientId,
                     BigDecimal amount,
                     BigDecimal amountBefore,
                     BigDecimal amountAfter,
                     OperationType type) {
        this.date = date;
        this.accCode = accCode;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
        this.type = type;
    }

    public Operation(String date,
                     @AllowedCurrency AccCode accCode,
                     long recipientId,
                     BigDecimal amount,
                     BigDecimal amountBefore,
                     BigDecimal amountAfter,
                     OperationType operationType) {
        this.date = date;
        this.accCode = accCode;
        this.recipientId = recipientId;
        this.amount = amount;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
        this.type = operationType;
    }
}
