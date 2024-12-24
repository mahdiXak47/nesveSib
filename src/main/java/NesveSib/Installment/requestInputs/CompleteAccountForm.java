package NesveSib.Installment.requestInputs;

import lombok.Getter;


@Getter
public class CompleteAccountForm {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;
    private final String dateOfBirth;
    private final String fathersName;
    private final String relativeNumber1;
    private final String relativeNumber2;
    private final String relativeNumber3;
    private boolean isActive;

    public CompleteAccountForm(String firstName, String lastName, String email, String address, String phoneNumber, String dateOfBirth, String fathersName, String relativeNumber1, String relativeNumber2, String relativeNumber3) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.fathersName = fathersName;
        this.relativeNumber1 = relativeNumber1;
        this.relativeNumber2 = relativeNumber2;
        this.relativeNumber3 = relativeNumber3;
    }
}
