package NesveSib.Installment.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productinstallmentdetailtbl")
public class InstallmentInformation {

    @Id
//    @GeneratedValue
    @Column(name = "installmentcode")
    private Integer installmentCode;

    @NonNull
    @Column(name = "customernationalid")
    private String customerNationalid;

    @NonNull
    @Column(name = "installmentpaymentdate")
    private Date invoiceDate;

    @NonNull
    @Column(name = "productid")
    private Integer productPurchasedId;

    @NonNull
    @Column(name = "sellernationalid")
    private String sellerNationalid;

    @NonNull
    @Column(name = "installmentindex")
    private Integer installmentIndex;

    @NonNull
    @Column(name = "installmentpricetopay")
    private Double installmentPriceToPay;

    @Override
    public String toString() {
        return "InstallmentInformation{" +
                "installmentCode=" + installmentCode +
                ", customerNationalid='" + customerNationalid + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", productPurchasedId='" + productPurchasedId + '\'' +
                ", sellerNationalid='" + sellerNationalid + '\'' +
                ", installmentIndex=" + installmentIndex +
                ", installmentPriceToPay=" + installmentPriceToPay +
                '}';
    }
}
