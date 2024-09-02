package NesveSib.Installment.dataProcessingModel;


import NesveSib.Installment.model.Money;
import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.model.users.Seller;
import com.github.mfathi91.time.PersianDate;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductPurchaseInputAndOutput {

    @NonNull
    private final Customer customer;

    @NonNull
    private final Product product;

    @NonNull
    private final Seller seller;

    @NonNull
    private final Date purchaseDate;

    @NonNull
    private final Double purchaseAmount;


}
