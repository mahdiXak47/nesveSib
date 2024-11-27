package NesveSib.Installment.model.users;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seller_store_shop_man_tbl")
@AllArgsConstructor
public class ShopMan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NonNull
    @Column(name = "national_id")
    private Long nationalId;

    @NonNull
    @Column(name = "store_id")
    private Integer storeId;

    public ShopMan() {
        System.out.println("inja naya bitch!");
    }

    @Override
    public String toString() {
        return "ShopMan{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", nationalId=" + nationalId +
                ", storeId=" + storeId +
                '}';
    }
}
