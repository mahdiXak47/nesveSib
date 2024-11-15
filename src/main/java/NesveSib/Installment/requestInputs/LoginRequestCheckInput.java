package NesveSib.Installment.requestInputs;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class LoginRequestCheckInput {

    @NonNull
    private final String nationalId;

    @NonNull
    private final String password;

    public LoginRequestCheckInput(@NonNull String input, @NonNull String password) {
        this.nationalId = input;
        this.password = password;
    }
}
