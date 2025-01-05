package NesveSib.Installment.model.users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "customer_phone_purchases_tbl")
public class CustomerPurchasedPhones {

    @Id
    @Column(name = "index")
    private int index;

    @Column(name = "customer_national_id")
    private String customerNationalId;

    @Column(name = "product_code")
    private int productCode;

    @Column(name = "seller_national_id")
    private String sellerNationalId;
}
