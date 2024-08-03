package NesveSib.Installment.service;


import NesveSib.Installment.dataProcessingModel.ProductPurchaseInputs;
import NesveSib.Installment.exceptions.OutputCode;
import org.springframework.stereotype.Service;

@Service
public class ProductPurchaseService {

    public OutputCode checkPurchaseRequirements(ProductPurchaseInputs inputs) {
        // TODO: check the purchase requirements!
        return OutputCode.SUCCESS_2001;
    }


    public void purchaseProduct(ProductPurchaseInputs input) {
        // TODO: register the product for seller and customer also create the purchase documents!
    }
}
