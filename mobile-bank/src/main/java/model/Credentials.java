package model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Credentials implements Serializable {

    private String login;
    private String password;
}
