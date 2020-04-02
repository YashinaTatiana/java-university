package com.university.accounts.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter @Setter
public class UserDto {

    @NotNull
    private Long id;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String address;
}
