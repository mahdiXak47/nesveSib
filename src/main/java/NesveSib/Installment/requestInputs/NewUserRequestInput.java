package NesveSib.Installment.requestInputs;


import lombok.NonNull;

public record NewUserRequestInput(@NonNull String national_number, @NonNull String phone_number,
                                  @NonNull String password, @NonNull String password_confirm) {

}
