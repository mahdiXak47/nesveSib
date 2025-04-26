package NesveSib.Installment.service;


import NesveSib.Installment.dataProcessingModel.InstallmentPurchasedInput;
import NesveSib.Installment.dataProcessingModel.ProductPurchaseInputAndOutput;
import NesveSib.Installment.model.InstallmentInformation;
import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.model.productModels.ProductPurchasedDetail;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ProductPurchaseService {

    private final PurchasedProductRepository purchasedProductRepository;
    private final InstallmentInformationRepository installmentInformationRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final SellerAccountRepository sellerAccountRepository;
    private final ProductsRepository productsRepository;

    public ProductPurchaseService(PurchasedProductRepository purchasedProductRepository,
                                  InstallmentInformationRepository installmentInformationRepository,
                                  CustomerAccountRepository customerAccountRepository,
                                  SellerAccountRepository sellerAccountRepository, ProductsRepository productsRepository) {

        this.purchasedProductRepository = purchasedProductRepository;
        this.installmentInformationRepository = installmentInformationRepository;
        this.customerAccountRepository = customerAccountRepository;
        this.sellerAccountRepository = sellerAccountRepository;
        this.productsRepository = productsRepository;
    }

    public boolean checkPurchaseRequirements(ProductPurchaseInputAndOutput inputs) {
        // TODO: check the purchase requirements!
        return true;
    }


    public void directPurchaseProduct(ProductPurchaseInputAndOutput input) {
        ProductPurchasedDetail purchasedProduct = new ProductPurchasedDetail(-1,
                input.getPurchaseAmount(), false, 0,
                input.getProduct().getProductCode(), input.getCustomer().getNationalId(),
                input.getSeller().getNationalId(), input.getPurchaseDate());

        purchasedProductRepository.save(purchasedProduct);

    }

    public void installmentPurchaseProduct(InstallmentPurchasedInput input) {
        ProductPurchasedDetail purchasedProduct = new ProductPurchasedDetail(-1,
                input.getPurchaseAmount(), true, input.getNumberOfInstallments(),
                input.getProduct().getProductCode(), input.getCustomer().getNationalId(),
                input.getSeller().getNationalId(), input.getPurchaseDate());

        for (int i = 0; i < input.getNumberOfInstallments(); i++) {
            InstallmentInformation installmentInformation = new InstallmentInformation(-1,
                    input.getCustomer().getNationalId(), input.getInstallmentDates().get(i), input.getProduct().getProductCode(),
                    input.getSeller().getNationalId(), i + 1, input.getInstallmentAmounts().get(i));

            installmentInformationRepository.save(installmentInformation);
        }
        purchasedProductRepository.save(purchasedProduct);
    }

    public boolean productFound(Integer productCode) {
        return purchasedProductRepository.existsById(productCode);
    }

    public ProductPurchaseInputAndOutput getPurchasedProductDetails(Integer productCode) {
        ProductPurchasedDetail product = purchasedProductRepository.getReferenceById(productCode);
        if (product.getIsProductPurchaseInInstallment()) {
            return new InstallmentPurchasedInput(getCustomerByNationalId(product.getCustomerNationalId()),
                    getProductByProductCode(productCode), getSellerByNationalId(product.getSellerNationalId()),
                    product.getPurchasedDate(), product.getProductPrice(), getPurchasedProductInstallments(productCode),
                    getPurchasedProductInstallmentAmount(productCode));

        }
        return new ProductPurchaseInputAndOutput(getCustomerByNationalId(product.getCustomerNationalId()),
                getProductByProductCode(productCode), getSellerByNationalId(product.getSellerNationalId()),
                product.getPurchasedDate(), product.getProductPrice());
    }

    private ArrayList<Double> getPurchasedProductInstallmentAmount(Integer productCode) {
        //TODO: getting all installment amounts of a purchased product
        return null;
    }

    private ArrayList<Date> getPurchasedProductInstallments(Integer productCode) {
        //TODO: getting all installments of a purchased product
        return null;
    }


    public Customer getCustomerByNationalId(String nationalId) {
        return customerAccountRepository.getReferenceById(nationalId);
    }

    public Product getProductByProductCode(Integer productCode) {
        return productsRepository.getReferenceById(productCode);
    }

    public Seller getSellerByNationalId(String nationalId) {
        return sellerAccountRepository.getReferenceById(nationalId);
    }
}
