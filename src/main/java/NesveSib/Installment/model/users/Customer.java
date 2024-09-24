package NesveSib.Installment.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customertbl")
public class Customer {
    @NonNull
    @Column(name = "nationalid")
    @Id
    private String nationalId;

    @NonNull
    @Column(name = "fathersname")
    private String fathersName;

    @NonNull
    @Column(name = "firstname")
    private String firstName;

    @NonNull
    @Column(name = "lastname")
    private String lastName;

    @NonNull
    @Column(name = "email")
    private String email;

    @Column(name = "emailverified")
    private boolean emailVerified;

    @NonNull
    @Column(name = "address")
    private String address;

    @NonNull
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "phoneverified")
    private boolean phoneVerified;

    @NonNull
    @Column(name = "encryptedpassword")
    private String encryptedPassword;

    @NonNull
    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    @NonNull
    @Column(name = "firstrelativephonenumber")
    private String relativePhoneNumber;

    @NonNull
    @Column(name = "secondrelativephonenumber")
    private String secondRelativePhoneNumber;

    @NonNull
    @Column(name = "thirdrelativephonenumber")
    private String thirdRelativePhoneNumber;

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
