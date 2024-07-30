package NesveSib.Installment.respository;

import NesveSib.Installment.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<Seller, String> {
}