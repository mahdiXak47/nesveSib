package NesveSib.Installment.respository;

import NesveSib.Installment.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByNameAndId(String tokenName, long userId);
    List<Token> findAllById(Long id);
    Optional<Token> findByIdAndToken(Long id, String token);
}
