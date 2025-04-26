package NesveSib.Installment.repository;


import NesveSib.Installment.model.users.SellerStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerStoreRepository extends JpaRepository<SellerStore, Long> {



    Optional<SellerStore> findBySellerNationalId(String sellerNationalId);
}

