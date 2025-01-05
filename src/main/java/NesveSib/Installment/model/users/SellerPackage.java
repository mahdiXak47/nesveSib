package NesveSib.Installment.model.users;

import lombok.Getter;

@Getter
public class SellerPackage {

    private final Seller sellerInfo;
    private final SellerStore sellerStore;

    public SellerPackage(Seller sellerInfo, SellerStore sellerStore) {
        this.sellerInfo = sellerInfo;
        this.sellerStore = sellerStore;
    }
}
