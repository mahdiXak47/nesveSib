package NesveSib.Installment.auth;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> findByUsername(String username);

    Optional<ApplicationUser> findByNationalId(String nationalId);

    Optional<ApplicationUser> findByPhoneNumber(String phoneNumber);

    Optional<ApplicationUser> findByEmail(String email);
}
