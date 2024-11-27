package NesveSib.Installment.model.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seller_store_investor_tbl")
public class SellerStoreInvestor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "investment_amount")
    private long investmentAmount;

//    @Column(name = "store_id",insertable=false, updatable=false)
    @Column(name = "store_id")
    private int storeId;

//    @ManyToOne
//    @JoinColumn(name = "store_id", nullable = false)
//    private SellerStore store;

    public SellerStoreInvestor(int id, String investorFirstName, String investorLastName, String investorPhoneNumber, String investmentAmount, Integer storeId) {
        this.id = id;
        this.firstName = investorFirstName;
        this.lastName = investorLastName;
        this.phoneNumber = investorPhoneNumber;
        this.investmentAmount = Long.parseLong(investmentAmount);
        this.storeId = storeId;
    }

    public SellerStoreInvestor() {
        System.out.println("should not come here!");
    }
}
