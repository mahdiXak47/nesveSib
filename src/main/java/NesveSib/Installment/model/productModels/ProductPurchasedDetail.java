package NesveSib.Installment.model.productModels;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchasedproducttbl")
public class ProductPurchasedDetail {

    @Id
    @Column(name = "purchasedproductcode")
    private Integer purchasedProductCode;

    @NonNull
    @Column(name = "productprice")
    private Double productPrice;

    @NonNull
    @Column(name = "productpurchaseininstallment")
    private Boolean isProductPurchaseInInstallment;

    @NonNull
    @Column(name = "productnumofinstallments")
    private Integer productNumOnInstallments;

    @NonNull
    @Column(name = "purchasedproductid")
    private Integer purchasedProduct;

    @NonNull
    @Column(name = "customernationalid")
    private String customerNationalId;

    @NonNull
    @Column(name = "sellernationalid")
    private String sellerNationalId;

    @NonNull
    @Column
    private Date purchasedDate;

    @Override
    public String toString() {
        return "ProductPurchasedDetail{" +
                "purchasedProductCode=" + purchasedProductCode +
                ", productPrice=" + productPrice +
                ", isProductPurchaseInInstallment=" + isProductPurchaseInInstallment +
                ", productNumOnInstallments=" + productNumOnInstallments +
                ", purchasedProduct=" + purchasedProduct +
                ", customerNationalId='" + customerNationalId + '\'' +
                ", sellerNationalId='" + sellerNationalId + '\'' +
                '}';
    }
}
