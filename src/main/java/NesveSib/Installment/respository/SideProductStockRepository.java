package NesveSib.Installment.respository;

import NesveSib.Installment.model.productModels.SideProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SideProductStockRepository extends JpaRepository<SideProductStock, Long> {
    List<SideProductStock> findBySellerCode(Integer sellerCode);

}
