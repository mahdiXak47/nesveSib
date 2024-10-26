package NesveSib.Installment.requestInputs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public record NewCustomerRequestInput(@NonNull String national_number, @NonNull String phone_number,
                                      @NonNull String password, @NonNull String password_confirm) {

}
