package NesveSib.Installment.respository;


import NesveSib.Installment.model.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends JpaRepository<Customer,String> {


    Optional<Customer> findByNationalId(String nationalId);


    Optional<Customer> findByEmail(String email);


    Optional<Customer> findByPhoneNumber(String phoneNumber);

}
    