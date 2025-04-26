package NesveSib.Installment.repository;

import NesveSib.Installment.model.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellerAccountRepository extends JpaRepository<Seller, String> {

//
//    @Query("SELECT x FROM Seller x where x.email = :emailAddress")
//    Optional<Seller> findSellerByEmail(@Param("emailAddress") String email);
//
//    @Query("SELECT x FROM Seller x where x.phoneNumber = :phoneNumber")
//    Optional<Seller> findSellerByPhoneNumber(@Param("phoneNumber") String phonenumber);

    Optional<Seller> findByNationalId(String nationalId);

    Optional<Seller> findByEmail(String email);

    Optional<Seller> findByPhoneNumber(String phoneNumber);

}
