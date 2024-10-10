package NesveSib.Installment.service;


import NesveSib.Installment.model.addingProductToStore.PhoneAddedToStore;
import NesveSib.Installment.model.productModels.Product;
import NesveSib.Installment.respository.ProductsRepository;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Service;


@Service
public class SellerDashboardService {

    private final Logger logger = ProjectInternalTools.getLogger(SellerDashboardService.class.getName());

    private final ProductsRepository productsRepository;

    public SellerDashboardService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public boolean addingNewPhoneToStoreStorage(PhoneAddedToStore phoneToBeAdded) {
        //TODO: casting PhoneAddedToStore to Product
        Product product = new Product(-1, phoneToBeAdded.getPhoneName(), "Iphone",
                phoneToBeAdded.getPhoneModel(), phoneToBeAdded.getFirstIMEI(), phoneToBeAdded.getSecondIMEI(),
                phoneToBeAdded.getPhoneColor());
        //TODO: implementation is not complete!
        productsRepository.save(product);
        return true;
    }

}
