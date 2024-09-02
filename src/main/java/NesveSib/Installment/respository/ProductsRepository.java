package NesveSib.Installment.respository;

import NesveSib.Installment.model.productModels.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {
}
