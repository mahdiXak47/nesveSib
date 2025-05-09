package NesveSib.Installment.repository;

import NesveSib.Installment.model.productModels.Airpod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirpodStockRepository extends JpaRepository<Airpod,Integer> {

    List<Airpod> findBySellerStoreCode(Integer sellerCode);

}
