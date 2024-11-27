package NesveSib.Installment.model.addingProductToStore;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SideProductToBeAddedToStore {

    private final String productType;

    private final String productName;

    private final Integer numberOfProductAvailable;

    private final String productPurchaseBySellerCost;
}
