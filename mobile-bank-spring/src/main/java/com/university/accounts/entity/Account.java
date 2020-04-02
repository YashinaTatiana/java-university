package com.university.accounts.entity;

import com.university.accounts.dto.CreateAccountDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "accCode")
    private AccCode currency;

    @Column(name = "isDefault")
    private Boolean isDefault;

    public Account(CreateAccountDto accountDto) {
        this.amount = accountDto.getAmount();
        this.isDefault = false;
        this.currency = accountDto.getAccCode();
    }
}
