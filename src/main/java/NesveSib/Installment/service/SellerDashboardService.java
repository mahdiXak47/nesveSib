package NesveSib.Installment.service;


import NesveSib.Installment.conttroller.AddInvestorForm;
import NesveSib.Installment.conttroller.AddShopManForm;
import NesveSib.Installment.model.addingProductToStore.PhoneToBeAddedToStore;
import NesveSib.Installment.model.addingProductToStore.SideProductToBeAddedToStore;
import NesveSib.Installment.model.productModels.PhoneStock;
import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.model.productModels.SideProductStock;
import NesveSib.Installment.model.users.SellerStoreInvestor;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.model.users.ShopMan;
import NesveSib.Installment.respository.*;
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
    private final SellerStoreRepository sellerStoreRepository;
    private final SellerStoreInvestorRepository sellerStoreInvestorRepository;
    private final SellerStoreShopmanRepository sellerStoreShopmanRepository;

    public SellerDashboardService(ProductsRepository productsRepository, SellerAccountRepository sellerAccountRepository, PhoneStockRepository phoneStockRepository, SideProductStockRepository sideProductStockRepository, SellerStoreRepository sellerStoreRepository, SellerStoreInvestorRepository sellerStoreInvestorRepository, SellerStoreShopmanRepository sellerStoreShopmanRepository) {
        this.productsRepository = productsRepository;
        this.phoneStockRepository = phoneStockRepository;
        this.sideProductStockRepository = sideProductStockRepository;
        this.sellerStoreRepository = sellerStoreRepository;
        this.sellerStoreInvestorRepository = sellerStoreInvestorRepository;
        this.sellerStoreShopmanRepository = sellerStoreShopmanRepository;
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
        SideProductStock product = new SideProductStock(-1, newProduct.getProductType(), newProduct.getProductName(),
                newProduct.getNumberOfProductAvailable(), newProduct.getProductPurchaseBySellerCost(), seller.getStoreId());
        sideProductStockRepository.save(product);
        return true;
    }

    public List<SideProductStock> getSideProductStockBySellerCode(Seller seller) {
        return sideProductStockRepository.findBySellerCode(seller.getStoreId());
    }


    public List<SellerStoreInvestor> getInvestorsByStoreId(Long storeId) {
//        return sellerStoreInvestorRepository.findByStoreSerialNumber(storeId);
        return null;
    }

    public boolean addingNewInvestorToStore(AddInvestorForm newInvestor, Seller seller) {
        SellerStoreInvestor investor = new SellerStoreInvestor(-1, newInvestor.getInvestorFirstName(),
                newInvestor.getInvestorLastName(), ProjectInternalTools.trimPhoneNumber(newInvestor.getInvestorPhoneNumber()),
                newInvestor.getInvestmentAmount(), seller.getStoreId());
        sellerStoreInvestorRepository.save(investor);
        return true;
    }

    public List<SellerStoreInvestor> getInvestorsBySellerStoreCode(Seller seller) {
        return sellerStoreInvestorRepository.findInvestorsByStoreId(Long.valueOf(seller.getStoreId()));
    }

    public boolean addingNewShopManToStore(AddShopManForm newShopMan, Seller seller) {
        ShopMan shopMan = new ShopMan(-1, newShopMan.getShopManFirstName(), newShopMan.getShopManLastName(),
                ProjectInternalTools.trimPhoneNumber(newShopMan.getShopManPhoneNumber()),
                Long.valueOf(newShopMan.getShopManNationalId()), seller.getStoreId());
        sellerStoreShopmanRepository.save(shopMan);
        return false;
    }

    public List<ShopMan> getStoreShopmansBySellerStoreCode(Seller seller) {
//        TODO: get the store shopman infos from database
        return null;
    }
}
