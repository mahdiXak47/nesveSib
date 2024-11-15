package NesveSib.Installment.respository;

import NesveSib.Installment.model.TokenCookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenCookieRepository extends JpaRepository<TokenCookie, String> {
    Optional<TokenCookie> findByToken(String token);
}