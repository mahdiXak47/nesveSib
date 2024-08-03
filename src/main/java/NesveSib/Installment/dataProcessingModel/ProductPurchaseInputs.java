package NesveSib.Installment.dataProcessingModel;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductPurchaseInputs {


    private final String sellerUsername;
    private final String sellerPassword;
    private final String sellerEmail;

    private final String productName;

    private final String customerUsername;
    private final String customerPassword;
    private final String customerNationalId;

}
