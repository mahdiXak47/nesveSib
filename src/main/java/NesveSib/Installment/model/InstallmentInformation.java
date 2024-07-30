package NesveSib.Installment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class InstallmentInformation {

    private final Long installmentId;
    private String customerFirstName;
    private String customerLastName;
    private String customerFathersName;
    private int customerNationalId;
    private Date invoiceDate;
    private String customerAddress;
    private String productModel;
    private String sellerFirstName;
    private String sellerLastName;
    private double productTotalPrice;
    private double advanceAmountPaid; // meghdar pardakht shode be onvane pishpardakht
    private double remainingAmountToPay; // meghdar baghi mande ke pardakht nashode
    private String customerPhoneNumber;
    private String customerSecondPhoneNumber;
    private String customerThirdPhoneNumber;
    private List<InstallmentToPay> installmentsToPay;
}
