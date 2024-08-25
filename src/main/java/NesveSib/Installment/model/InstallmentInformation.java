package NesveSib.Installment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "productinstallmentdetailtbl")
public class InstallmentInformation {

    @Id
    @Column(name = "installmentcode")
    private final Long installmentCode;

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
