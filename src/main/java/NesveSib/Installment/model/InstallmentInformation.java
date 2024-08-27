package NesveSib.Installment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "productinstallmentdetailtbl")
public class InstallmentInformation {

    @Id
//    @GeneratedValue
    @Column(name = "installmentcode")
    private Long installmentCode;

    @NonNull
    @Column(name = "customernationalid")
    private String customerNationalid;

    @NonNull
    @Column(name = "installmentpaymentdate")
    private Date invoiceDate;

    @NonNull
    @Column(name = "productid")
    private String productPurchasedId;

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
