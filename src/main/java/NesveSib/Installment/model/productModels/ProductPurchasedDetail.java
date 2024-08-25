package NesveSib.Installment.model.productModels;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
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
