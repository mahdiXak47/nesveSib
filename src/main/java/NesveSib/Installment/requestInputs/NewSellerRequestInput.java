package NesveSib.Installment.requestInputs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class NewSellerRequestInput {

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final String username;

    @NonNull
    private final String nationalId;

    @NonNull
    private final String phoneNumber;

    @NonNull
    private final String email;

    @NonNull
    private final String storeAddress;

    @NonNull
    private final Integer storePlate;

    @NonNull
    private final String storeName;

    @NonNull
    private final String password;

}
