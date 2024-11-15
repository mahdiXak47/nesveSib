package NesveSib.Installment.service;


import NesveSib.Installment.model.addingProductToStore.PhoneToBeAddedToStore;
import NesveSib.Installment.model.addingProductToStore.SideProductToBeAddedToStore;
import NesveSib.Installment.model.productModels.PhoneStock;
import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.model.productModels.SideProductStock;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.respository.PhoneStockRepository;
import NesveSib.Installment.respository.ProductsRepository;
import NesveSib.Installment.respository.SellerAccountRepository;
import NesveSib.Installment.respository.SideProductStockRepository;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SellerDashboardService {

    private final Logger logger = ProjectInternalTools.getLogger(SellerDashboardService.class.getName());

    private final ProductsRepository productsRepository;
    private final PhoneStockRepository phoneStockRepository;
    private final SideProductStockRepository sideProductStockRepository;

    public SellerDashboardService(ProductsRepository productsRepository, SellerAccountRepository sellerAccountRepository, PhoneStockRepository phoneStockRepository, SideProductStockRepository sideProductStockRepository) {
        this.productsRepository = productsRepository;
        this.phoneStockRepository = phoneStockRepository;
        this.sideProductStockRepository = sideProductStockRepository;
    }

    public boolean addingNewPhoneToStoreStorage(PhoneToBeAddedToStore phoneToBeAdded, Seller ownerOfProduct) {
        Product product = new Product(-1, "Iphone", "Phone",
                phoneToBeAdded.getPhoneModel(), phoneToBeAdded.getPhoneFirstIMEI(), phoneToBeAdded.getPhoneSecondIMEI(),
                phoneToBeAdded.getPhoneColor(), ownerOfProduct);
        PhoneStock phoneStock = new PhoneStock(-1, phoneToBeAdded.getPhoneModel(), phoneToBeAdded.getPhoneType(),
                phoneToBeAdded.getPhoneColor(), phoneToBeAdded.getPhonePartNumber(), phoneToBeAdded.getPhoneStorage(),
                phoneToBeAdded.getPhoneFirstIMEI(), phoneToBeAdded.getPhoneSecondIMEI(), phoneToBeAdded.getPhonePurchasedCost(),
                ownerOfProduct.getStoreId());
        phoneStockRepository.save(phoneStock);
        productsRepository.save(product);
        return true;
    }

    public List<PhoneStock> getPhoneStockBySellerCode(Seller seller) {
        return phoneStockRepository.findBySellerCode(seller.getStoreId());
    }

    public boolean addingNewSideProductToStoreStorage(SideProductToBeAddedToStore newProduct, Seller seller) {
        SideProductStock product = new SideProductStock(-1,newProduct.getSideProductType(),newProduct.getSideProductName(),
                newProduct.getNumberOfProductAvailable(),newProduct.getProductPurchaseBySellerCost(),seller.getStoreId());
        sideProductStockRepository.save(product);
        return true;
    }

    public List<SideProductStock> getSideProductStockBySellerCode(Seller seller) {
        return sideProductStockRepository.findBySellerCode(seller.getStoreId());
    }
}
