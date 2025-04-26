package NesveSib.Installment.repository;

import NesveSib.Installment.model.productModels.PhoneStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneStockRepository extends JpaRepository<PhoneStock, Long> {

    List<PhoneStock> findBySellerStoreId(Integer sellerCode);

}
