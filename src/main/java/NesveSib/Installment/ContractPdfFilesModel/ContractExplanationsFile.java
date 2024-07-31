package NesveSib.Installment.ContractPdfFilesModel;


import NesveSib.Installment.model.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Getter
@RequiredArgsConstructor
public class ContractExplanationsFile {


    private final String sellerFirstAndLastName;
    private final String sellerAddress;
    private final String sellerPhoneNumber;
    private final String sellerSecondPhoneNumber;
    private final String customerFirstAndLastName;
    private final String customerAddress;
    private final String customerPhoneNumber;
    private final String customerSecondPhoneNumber;
    private final String customerFathersName;
    private final String customerNationalId;

    private final String productName;
    private final String productModel;
    private final String productSerialNumber;

    private final Date contractDateStarted;
    private final Date contractDateEnded;

    private final Money productTotalPrice;
    private final Money totalPricePaid;
    private final Money totalPriceRemained;

    private final HashMap<Date, Money> productInstallments;


}
