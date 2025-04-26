package NesveSib.Installment.repository;

import NesveSib.Installment.model.users.ShopMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerStoreShopmanRepository extends JpaRepository<ShopMan,Integer> {


    @Query(value = "SELECT * FROM seller_store_shop_man_tbl WHERE store_id = :storeId", nativeQuery = true)
    List<ShopMan> findShopMenByStoreId(@Param("storeId") Integer storeId);
}
