package com.university.accounts.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Credentials {
    private String username;
    private String password;
}
