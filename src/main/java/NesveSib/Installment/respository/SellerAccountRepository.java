package NesveSib.Installment.respository;

import NesveSib.Installment.model.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellerAccountRepository extends JpaRepository<Seller,String> {


    @Query("SELECT x FROM Seller x where x.username = :usernameToCheck")
    Optional<Seller> findSellerByUsername(@Param("usernameToCheck") String username);

    @Query("SELECT x FROM Seller x where x.email = :emailAddress")
    Optional<Seller> findSellerByEmail(@Param("emailAddress") String email);

    @Query("SELECT x FROM Seller x where x.phoneNumber = :phoneNumber")
    Optional<Seller> findSellerByPhoneNumber(@Param("phoneNumber") String phonenumber);
}
