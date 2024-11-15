package NesveSib.Installment.model.addingProductToStore;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SideProductToBeAddedToStore {

    private final String sideProductType;

    private final String sideProductName;

    private final Integer numberOfProductAvailable;

    private final Long productPurchaseBySellerCost;
}
