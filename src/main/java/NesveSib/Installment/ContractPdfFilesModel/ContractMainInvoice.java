package NesveSib.Installment.ContractPdfFilesModel;


import NesveSib.Installment.model.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Getter
@RequiredArgsConstructor
public class ContractMainInvoice {

    private final String customerFirstAndLastName;
    private final String customerPhoneNumber;
    private final String customerSecondPhoneNumber;
    private final String customerThirdPhoneNumber;
    private final String customerAddress;

    private final String productName;
    private final String productModel;
    private final Money productTotalPrice;
    private final Money productTotalAmountPaid;
    private final Money productTotalAmountRemained;
    private final String IMEI;

    private final String id;
    private final String code;

    private final Date dateContractSigned;

    private final HashMap<Date, Money> installmentDetails;
    private final HashMap<Integer, String> installmentNumberAndExplanations;


}
