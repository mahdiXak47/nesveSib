package NesveSib.Installment.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


import java.util.Date;



@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "generalusertbl")
//@Table(name = "customertbl")
public class Customer extends User{

//    @NonNull
//    @Column(name = "fathersname")
//    private String fathersName;
//
//    @NonNull
//    @Column(name = "dateofbirth")
//    private Date dateOfBirth;
//
//    @NonNull
//    @Column(name = "firstrelativephonenumber")
//    private String relativePhoneNumber;
//
//    @NonNull
//    @Column(name = "secondrelativephonenumber")
//    private String secondRelativePhoneNumber;
//
//    @NonNull
//    @Column(name = "thirdrelativephonenumber")
//    private String thirdRelativePhoneNumber;

//    private List<Product> customerPurchasedProducts;

//    @Override
//    public String toString() {
//        return "nationalId is " + super.getNationalId() + ", firstName is " + super.getFirstName() + ", lastName is " + super.getLastName()
//                + "\n, username is " + super.getUsername()
//                + "\n, fathersName is " + fathersName
//                + "\n, email is " + super.getEmail()
//                + "\n, phone number is " + super.getPhoneNumber()
//                + "\n, address is " + super.getAddress()
//                + "\n, emailVerified is " + super.isEmailVerified()
//                + "\n, phoneVerified is " + super.isPhoneVerified()
//                + "\n, dateOfBirth is " + dateOfBirth
//                + "\n, encryptedPassword is " + super.getEncryptedPassword()
//                + "\n, relativePhoneNumber is " + relativePhoneNumber
//                + "\n, secondRelativePhoneNumber is " + secondRelativePhoneNumber;
//    }
}
