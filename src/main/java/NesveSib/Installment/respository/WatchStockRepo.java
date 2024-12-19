package NesveSib.Installment.respository;

import NesveSib.Installment.model.addingProductToStore.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchStockRepo extends JpaRepository<Watch,Integer> {

}
