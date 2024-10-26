package NesveSib.Installment.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "customer_tbl")
public class Customer {
    @NonNull
    @Column(name = "national_id")
    @Id
    private String nationalId;

    @Column(name = "fathers_name")
    private String fathersName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "address")
    private String address;

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_verified")
    private boolean phoneVerified;

    @NonNull
    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "first_relative_phone_number")
    private String relativePhoneNumber;

    @Column(name = "second_relative_phone_number")
    private String secondRelativePhoneNumber;

    @Column(name = "third_relative_phone_number")
    private String thirdRelativePhoneNumber;

    public Customer(@NonNull String nationalId, @NonNull String phoneNumber, @NonNull String password) {
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.encryptedPassword = password;
    }

    public Customer() {

    }

//    private List<Product> customerPurchasedProducts;

    @Override
    public String toString() {
        return "nationalId is " + this.nationalId + ", firstName is " + this.firstName + ", lastName is " + this.lastName
                + "\n, fathersName is " + this.fathersName
                + "\n, email is " + this.email
                + "\n, phone number is " + this.phoneNumber
                + "\n, address is " + this.address
                + "\n, emailVerified is " + this.emailVerified
                + "\n, phoneVerified is " + this.phoneVerified
                + "\n, dateOfBirth is " + this.dateOfBirth
                + "\n, encryptedPassword is " + this.encryptedPassword
                + "\n, relativePhoneNumber is " + this.relativePhoneNumber
                + "\n, secondRelativePhoneNumber is " + this.secondRelativePhoneNumber
        ;
    }
}
