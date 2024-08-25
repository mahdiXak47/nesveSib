package NesveSib.Installment.model.productModels;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@Setter
@Entity
@Table(name = "purchasedproducttbl")
public class ProductPurchasedDetail {

    @Id
    @Column(name = "purchasedproductcode")
    private final Integer purchasedProductCode;

    @NonNull
    @Column(name = "productprice")
    private final Double productPrice;

    @NonNull
    @Column(name = "productpurchaseininstallment")
    private final Boolean isProductPurchaseInInstallment;

    @NonNull
    @Column(name = "productnumofinstallments")
    private final Integer productNumOnInstallments;

    @NonNull
    @Column(name = "purchasedproductid")
    private final Integer purchasedProduct;

    @NonNull
    @Column(name = "customernationalid")
    private final String customerNationalId;

    @NonNull
    @Column(name = "sellernationalid")
    private final String sellerNationalId;

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
