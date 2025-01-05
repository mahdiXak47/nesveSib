package NesveSib.Installment.model.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "seller_store_tbl", uniqueConstraints = {
        @UniqueConstraint(columnNames = "seller_national_id")}
)
@AllArgsConstructor
@NoArgsConstructor
public class SellerStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @Column(name = "plate_number", nullable = false)
    private Integer plateNumber;

    @Column(name = "workplace", nullable = false)
    private String workplace;

    @Column(name = "seller_national_id")
    private String sellerNationalId;


//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SellerStoreInvestor> investors;

}
