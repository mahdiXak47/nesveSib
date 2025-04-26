package NesveSib.Installment.service;


import NesveSib.Installment.controller.AddInvestorForm;
import NesveSib.Installment.controller.AddShopManForm;
import NesveSib.Installment.model.productModels.Airpod;
import NesveSib.Installment.model.addingProductToStore.SideProductToBeAddedToStore;
import NesveSib.Installment.model.productModels.Watch;
import NesveSib.Installment.model.productModels.PhoneStock;
import NesveSib.Installment.model.productModels.SideProductStock;
import NesveSib.Installment.model.users.SellerStoreInvestor;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.model.users.ShopMan;
import NesveSib.Installment.repository.*;
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
    private final AirpodStockRepository airpodStockRepository;
    private final WatchStockRepo watchStockRepository;

    public SellerDashboardService(ProductsRepository productsRepository, SellerAccountRepository sellerAccountRepository, PhoneStockRepository phoneStockRepository, SideProductStockRepository sideProductStockRepository, SellerStoreRepository sellerStoreRepository, SellerStoreInvestorRepository sellerStoreInvestorRepository, SellerStoreShopmanRepository sellerStoreShopmanRepository, AirpodStockRepository airpodStockRepository, WatchStockRepo watchStockRepository) {
        this.productsRepository = productsRepository;
        this.phoneStockRepository = phoneStockRepository;
        this.sideProductStockRepository = sideProductStockRepository;
        this.sellerStoreRepository = sellerStoreRepository;
        this.sellerStoreInvestorRepository = sellerStoreInvestorRepository;
        this.sellerStoreShopmanRepository = sellerStoreShopmanRepository;
        this.airpodStockRepository = airpodStockRepository;
        this.watchStockRepository = watchStockRepository;
    }


    public List<PhoneStock> getPhoneStockBySellerCode(Seller seller) {
        return phoneStockRepository.findBySellerStoreId(seller.getStoreId());
    }

    public boolean addingNewSideProductToStoreStorage(SideProductToBeAddedToStore newProduct, Seller seller) {
//        SideProductStock product = new SideProductStock(-1, newProduct.getProductType(), newProduct.getProductName(),
//                newProduct.getNumberOfProductAvailable(), newProduct.getProductPurchaseBySellerCost(), seller.getStoreId());
//        sideProductStockRepository.save(product);
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
        if (sellerStoreShopmanRepository.findShopMenByStoreId(seller.getStoreId()).size()<4) {
            ShopMan shopMan = new ShopMan(-1, newShopMan.getShopManFirstName(), newShopMan.getShopManLastName(),
                    ProjectInternalTools.trimPhoneNumber(newShopMan.getShopManPhoneNumber()),
                    newShopMan.getShopManNationalId(), seller.getStoreId());
            sellerStoreShopmanRepository.save(shopMan);
            return true;
        } else {
            return false;
        }
    }

    public List<ShopMan> getStoreShopmansBySellerStoreCode(Seller seller) {
        return sellerStoreShopmanRepository.findShopMenByStoreId(seller.getStoreId());
    }

    public boolean addingNewAirpodToStoreStorage(Airpod newAirpod, Seller seller) {
        newAirpod.setSellerStoreCode(seller.getStoreId());
        airpodStockRepository.save(newAirpod);
        return true;
    }


    public List<Airpod> getAirpodStockBySellerCode(Integer StoreId) {
        return airpodStockRepository.findBySellerStoreCode(StoreId);
    }

    public boolean addingNewWatchToStore(Watch newWatch, Integer storeId) {
        newWatch.setSellerStoreCode(storeId);
        watchStockRepository.save(newWatch);
        return true;
    }

    public List<Watch> getWatchStockBySellerCode(Integer storeId) {
        return watchStockRepository.findBySellerStoreCode(storeId);
    }

    public boolean addingNewPhoneToStoreStorage(PhoneStock newPhone, Integer storeId) {
        newPhone.setSellerStoreId(storeId);
        phoneStockRepository.save(newPhone);
        return true;
    }
}
