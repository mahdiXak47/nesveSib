package NesveSib.Installment.model.productModels;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "seller_store_side_product_stock")
public class SideProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer productId;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String productName;

    @Column(name = "number_of_available")
    private Integer numberOfAvailable;

    @Column(name = "cost_for_seller")
    private String costForSeller;

    @Column(name = "product_seller_store_id")
    private Integer sellerCode;

    public SideProductStock() {
        System.out.println("system error in side product stock");
    }

    @Override
    public String toString() {
        return "SideProductStock{" +
                "productId=" + productId +
                ", category='" + category + '\'' +
                ", productName='" + productName + '\'' +
                ", numberOfAvailable=" + numberOfAvailable +
                ", costForSeller='" + costForSeller + '\'' +
                ", sellerCode=" + sellerCode +
                '}';
    }
}
