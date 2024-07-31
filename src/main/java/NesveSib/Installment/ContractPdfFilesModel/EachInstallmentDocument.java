package NesveSib.Installment.ContractPdfFilesModel;


import NesveSib.Installment.model.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class EachInstallmentDocument {

    private final String costumerFirstAndLastName;
    private final Date installmentDate;
    private final Money installmentAmount;
    private final String code;
    private final String id;
    private final String IMEI;
    private final String phoneNumber; // whose phone number ?
    private final Date documentDate;


}
