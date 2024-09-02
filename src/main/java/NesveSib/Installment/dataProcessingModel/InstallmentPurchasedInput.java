package NesveSib.Installment.dataProcessingModel;

import NesveSib.Installment.model.Money;
import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.model.users.Seller;
import com.github.mfathi91.time.PersianDate;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Date;

@Getter
public class InstallmentPurchasedInput extends ProductPurchaseInputAndOutput{

    private final ArrayList<Date> installmentDates;
    private final ArrayList<Double> installmentAmounts;

    private final  Integer numberOfInstallments;

    public InstallmentPurchasedInput(@NonNull Customer customer, @NonNull Product product, @NonNull Seller seller,
                                     @NonNull Date purchaseDate, @NonNull Double purchaseAmount,
                                     @NonNull ArrayList<Date> installmentDates,@NonNull ArrayList<Double> installmentAmounts) {

        super(customer, product, seller, purchaseDate, purchaseAmount);
        this.installmentDates = installmentDates;
        this.installmentAmounts = installmentAmounts;
        this.numberOfInstallments = installmentDates.size();
    }
}
