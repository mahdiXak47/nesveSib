package NesveSib.Installment.service;


import NesveSib.Installment.dataProcessingModel.ProductPurchaseInputs;
import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.productModels.ProductPurchasedDetail;
import NesveSib.Installment.respository.InstallmentInformationRepository;
import NesveSib.Installment.respository.PurchasedProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductPurchaseService {

    private final PurchasedProductRepository purchasedProductRepository;
    private final InstallmentInformationRepository installmentInformationRepository;

    public ProductPurchaseService(PurchasedProductRepository purchasedProductRepository,
                                  InstallmentInformationRepository installmentInformationRepository) {
        this.purchasedProductRepository = purchasedProductRepository;
        this.installmentInformationRepository = installmentInformationRepository;
    }

    public boolean checkPurchaseRequirements(ProductPurchaseInputs inputs) {
        // TODO: check the purchase requirements!
        return true;
    }


    public void directPurchaseProduct(ProductPurchaseInputs input) {
        ProductPurchasedDetail purchasedProduct = new ProductPurchasedDetail(-1,
                input.getPurchaseAmount().getAmount(),false,0,
                input.getProduct().getProductCode(),input.getCustomer().getNationalId(),input.getSeller().getNationalId());

        purchasedProductRepository.save(purchasedProduct);

    }

    public void installmentPurchaseProduct(ProductPurchaseInputs input,Integer numberOfInstallments) {
        ProductPurchasedDetail purchasedProduct = new ProductPurchasedDetail(-1,
                input.getPurchaseAmount().getAmount(),true,numberOfInstallments,
                input.getProduct().getProductCode(),input.getCustomer().getNationalId(),input.getSeller().getNationalId());

        purchasedProductRepository.save(purchasedProduct);

    }
}
