package NesveSib.Installment.model.users;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "seller_store_tbl")
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

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SellerStoreInvestor> investors;

}
