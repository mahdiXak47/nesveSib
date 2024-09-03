package NesveSib.Installment.model.users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sellertbl")
public class Seller {

    @NonNull
    @Column(name = "firstname")
    private String firstName;

    @NonNull
    @Column(name = "lastname")
    private String lastName;

    @NonNull
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "nationalid")
    @Id
    private String nationalId;

    @NonNull
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "phoneverified")
    private boolean phoneVerified;

    @NonNull
    @Column(name = "email")
    private String email;

    @Column(name = "emailverified")
    private boolean emailVerified;

    @NonNull
    @Column(name = "storeaddress")
    private String storeAddress;

    @NonNull
    @Column(name = "encryptedpassword")
    private String encryptedPassword;

    @NonNull
    @Column(name = "storeplate")
    private Integer storePlate;

    @NonNull
    @Column(name = "storename")
    private String storeName;

//    private List<Product> sellerSoledProducts;

    @Override
    public String toString() {
        return "Seller NationalId: " + this.getNationalId() + ", Username: " + this.getUsername()
                + "\n, First Name: " + this.getFirstName() + ", Last Name: " + this.getLastName()
                + "\n, Email: " + this.getEmail() + ", Phone Number: " + this.getPhoneNumber()
                + "\n, Email Verified: " + this.isEmailVerified() + ", Phone Verified: " + this.isPhoneVerified()
                + "\n, Address: " + this.getStoreAddress() + ", Store Plate: " + storePlate
                + "\n, Encrypted Password: " + this.getEncryptedPassword();
    }
}
