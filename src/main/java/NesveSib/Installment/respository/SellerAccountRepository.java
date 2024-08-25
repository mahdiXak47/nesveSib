package NesveSib.Installment.respository;

import NesveSib.Installment.model.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerAccountRepository extends JpaRepository<Seller,String> {

}
