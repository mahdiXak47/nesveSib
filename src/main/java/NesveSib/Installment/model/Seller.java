package NesveSib.Installment.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Seller {

    @NonNull
    private String nationalId;

    @NonNull
    private String username;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    private boolean emailVerified;

    @NonNull
    private String phoneNumber;

    private boolean phoneVerified;

    @NonNull
    private String address;

    private int storePlate;

    private String encryptedPassword;

    @Override
    public String toString() {
        return "Seller NationalId: " + nationalId + ", Username: " + username
                + "\n, First Name: " + firstName + ", Last Name: " + lastName
                + "\n, Email: " + email + ", Phone Number: " + phoneNumber
                + "\n, Email Verified: " + emailVerified + ", Phone Verified: " + phoneVerified
                + "\n, Address: " + address + ", Store Plate: " + storePlate
                + "\n, Encrypted Password: " + encryptedPassword;
    }
}
