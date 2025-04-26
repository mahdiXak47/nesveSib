package NesveSib.Installment.repository;

import NesveSib.Installment.model.productModels.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchStockRepo extends JpaRepository<Watch,Integer> {

    List<Watch> findBySellerStoreCode(Integer sellerCode);

}
