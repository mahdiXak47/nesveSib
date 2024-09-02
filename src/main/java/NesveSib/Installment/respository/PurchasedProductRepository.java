package NesveSib.Installment.respository;

import NesveSib.Installment.model.productModels.ProductPurchasedDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedProductRepository extends JpaRepository<ProductPurchasedDetail,Integer> {
}
