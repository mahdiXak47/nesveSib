package NesveSib.Installment.respository;


import NesveSib.Installment.model.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<Customer,String> {

}
