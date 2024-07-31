package NesveSib.Installment.ContractPdfFilesModel;

import NesveSib.Installment.model.Money;
import NesveSib.Installment.model.enums.TypeOfGuarantee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;


@RequiredArgsConstructor
@Getter
public class SellerArchiveContract {

    /*TODO: this class will be created to create a pdf form Contract for sellers to archive in their sales list*/

    private final String customerFirstAndLastName;
    private final String customerAddress;
    private final String customerPhoneNumber;
    private final String customerFathersName;
    private final String customerNationalId;
    private final Date contractDate;
    private final String phoneNumberModel;
    private final Money totalProductAmount;
    private final Money totalAmountPaid;
    private final Money totalAmountRemained;
    private final String sellerFirstAndLastName;
    private final String customerRelativePhoneNumber;
    private final String customerRelativeSecondPhoneNumber;
    private final HashMap<Date,Money> installments;
    private final String nameOfProductOwner;
    private final TypeOfGuarantee guaranteeType;
    private final String explanations;

}
