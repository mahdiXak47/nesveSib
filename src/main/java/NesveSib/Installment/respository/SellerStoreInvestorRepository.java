package NesveSib.Installment.respository;

import NesveSib.Installment.model.users.SellerStoreInvestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerStoreInvestorRepository extends JpaRepository<SellerStoreInvestor, Long> {

    @Query(value = "SELECT * FROM seller_store_investor_tbl WHERE store_id = :storeId", nativeQuery = true)
    List<SellerStoreInvestor> findInvestorsByStoreId(@Param("storeId") Long storeId);

}
