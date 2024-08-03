package NesveSib.Installment.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class Customer {

    private String nationalId;
    private String firstName;
    private String lastName;
    private String username;
    private String fathersName;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean emailVerified;
    private boolean phoneVerified;
    private Date dateOfBirth;
    private String encryptedPassword;
    private String relativePhoneNumber;
    private String secondRelativePhoneNumber;
    private List<Product> customerPurchasedProducts;


    @Override
    public String toString() {
        return "nationalId is " + nationalId + ", firstName is " + firstName + ", lastName is " + lastName
                + "\n, username is " + username
                + "\n, fathersName is " + fathersName
                + "\n, email is " + email
                + "\n, phone number is " + phoneNumber
                + "\n, address is " + address
                + "\n, emailVerified is " + emailVerified
                + "\n, phoneVerified is " + phoneVerified
                + "\n, dateOfBirth is " + dateOfBirth
                + "\n, encryptedPassword is " + encryptedPassword
                + "\n, relativePhoneNumber is " + relativePhoneNumber
                + "\n, secondRelativePhoneNumber is " + secondRelativePhoneNumber;
    }
}
