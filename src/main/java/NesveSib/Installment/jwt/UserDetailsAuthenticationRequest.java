package NesveSib.Installment.jwt;

import lombok.Getter;

@Getter
public class UserDetailsAuthenticationRequest {
    private final String username;
    private final String password;

    public UserDetailsAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
