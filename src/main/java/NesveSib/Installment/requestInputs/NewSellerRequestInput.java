package NesveSib.Installment.requestInputs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewSellerRequestInput {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String nationalId;
    private final String phoneNumber;
    private final String email;
    private final String storeAddress;
    private final Integer storePlate;
    private final String storeName;
    private final String password;

}
