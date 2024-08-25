package NesveSib.Installment.dataProcessingModel;


import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.model.users.Seller;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductPurchaseInputs {

    private final Customer customer;
    private final Product product;
    private final Seller seller;

}
