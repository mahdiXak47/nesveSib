package NesveSib.Installment.model.users;


import NesveSib.Installment.model.productModels.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "seller_tbl", uniqueConstraints = {
        @UniqueConstraint(columnNames = "national_id"),
        @UniqueConstraint(columnNames = "phone_number")
})
public class Seller {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Id
    @NonNull
    @Column(name = "national_id")
    private String nationalId;

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_verified")
    private boolean phoneVerified;

    @Column(name = "email")
    private String email;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @NonNull
    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products;


    private Integer storeId;

//    @Column(name = "is_active")
//    private boolean isActive;
//
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    public Seller(@NonNull String nationalId, @NonNull String phoneNumber, @NonNull String encryptedPassword) {
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.encryptedPassword = encryptedPassword;
//        this.isActive = false;
    }

    public Seller() {

    }

    @Override
    public String toString() {
        return "Seller NationalId: " + this.getNationalId() + ", Username: "
                + "\n, First Name: " + this.getFirstName() + ", Last Name: " + this.getLastName()
                + "\n, Email: " + this.getEmail() + ", Phone Number: " + this.getPhoneNumber()
                + "\n, Email Verified: " + this.isEmailVerified() + ", Phone Verified: " + this.isPhoneVerified()
                + "\n, Encrypted Password: " + this.getEncryptedPassword();
    }
}
