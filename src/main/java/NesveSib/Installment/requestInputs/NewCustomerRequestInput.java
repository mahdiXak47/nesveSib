package NesveSib.Installment.requestInputs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class NewCustomerRequestInput {

    @NonNull
    private final String nationalId;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final String fathersName;

    @NonNull
    private final String email;

    @NonNull
    private final String phoneNumber;

    @NonNull
    private final String address;

    @NonNull
    private final Date dateOfBirth;

    @NonNull
    private final String firstRelativePhoneNumber;

    @NonNull
    private final String secondRelativePhoneNumber;

    @NonNull
    private final String thirdRelativePhoneNumber;

    @NonNull
    private final String password;
}
