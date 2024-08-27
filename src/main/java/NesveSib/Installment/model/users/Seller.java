package NesveSib.Installment.model.users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generalusertbl")
//@Table(name = "sellertbl")
public class Seller extends User {

//    @NonNull
//    @Column(name = "storeplate")
//    private Integer storePlate;

//    @NonNull
//    @Column(name = "storename")
//    private String storeName;

//    private List<Product> sellerSoledProducts;

    @Override
    public String toString() {
        return "Seller NationalId: " + super.getNationalId() + ", Username: " + super.getUsername()
                + "\n, First Name: " + super.getFirstName() + ", Last Name: " + super.getLastName()
                + "\n, Email: " + super.getEmail() + ", Phone Number: " + super.getPhoneNumber()
                + "\n, Email Verified: " + super.isEmailVerified() + ", Phone Verified: " + super.isPhoneVerified()
//                + "\n, Address: " + super.getAddress() + ", Store Plate: " + storePlate
                + "\n, Encrypted Password: " + super.getEncryptedPassword();
    }
}
