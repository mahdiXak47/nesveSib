package NesveSib.Installment.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "generalusertbl")
public abstract class User {

    @NonNull
    @Column(name = "nationalid")
    @Id
    private String nationalId;

    @NonNull
    @Column(name = "username")
    private String username;

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

}
