package com.university.accounts.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter @Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "login")
    private String login;

    @NotEmpty
    @Column(name="password")
    private String password;

    @NotEmpty
    @Column(name = "address")
    private String address;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accountList;

    public User(String login, String password, String phone, String address) {
        this.phone = phone;
        this.login = login;
        this.password = password;
        this.address = address;
        this.accountList = null;
    }

    public void setAccountList(Account account) {
        if (this.accountList == null || this.accountList.isEmpty()){
            this.accountList = new ArrayList<>();
            account.setIsDefault(true);
        }
        accountList.add(account);
    }
}
