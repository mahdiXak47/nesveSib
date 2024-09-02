package NesveSib.Installment.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static NesveSib.Installment.security.ApplicationUserRule.*;

@Repository
public class ApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(
                        "mahdixak",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "mmdjvd",
                        passwordEncoder.encode("password"),
                        SELLER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "arshiash",
                        passwordEncoder.encode("password"),
                        CUSTOMER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
    }

    @Override
    public Optional<ApplicationUser> findByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<ApplicationUser> findByNationalId(String nationalId) {
        return Optional.empty();
    }

    @Override
    public Optional<ApplicationUser> findByPhoneNumber(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<ApplicationUser> findByEmail(String email) {
        return Optional.empty();
    }
}
