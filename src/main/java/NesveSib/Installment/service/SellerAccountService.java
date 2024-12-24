package NesveSib.Installment.service;


import NesveSib.Installment.model.TokenCookie;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.requestInputs.NewUserRequestInput;
import NesveSib.Installment.respository.SellerAccountRepository;
import NesveSib.Installment.respository.TokenCookieRepository;
import NesveSib.Installment.security.PasswordEncryptor;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SellerAccountService {

    private final Logger logger = ProjectInternalTools.getLogger(SellerAccountService.class.getName());

    private final SellerAccountRepository sellerAccountRepository;

    private final TokenCookieRepository tokenCookieRepository;

    private final PasswordEncryptor passwordEncryptor;

    public SellerAccountService(SellerAccountRepository sellerAccountRepository, PasswordEncryptor passwordEncryptor, TokenCookieRepository tokenCookieRepository) {
        this.sellerAccountRepository = sellerAccountRepository;
        this.tokenCookieRepository = tokenCookieRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public void createRawSellerAccount(NewUserRequestInput input) {
        Seller seller = new Seller(input.national_number(), ProjectInternalTools.trimPhoneNumber(input.phone_number()), passwordEncryptor.encryptPassword(input.password()));
        seller.setPhoneNumber(ProjectInternalTools.trimPhoneNumber(seller.getPhoneNumber()));
        seller.setEncryptedPassword(ProjectInternalTools.passwordEncryptor.encryptPassword(seller.getEncryptedPassword()));
        sellerAccountRepository.save(seller);
        logger.info("createSellerAccount: seller added to database successfully!");
    }


    public boolean checkIfSellerExisted(String toBeChecked, int type) {
        return switch (type) {
            case 1 -> sellerAccountRepository.findByNationalId(toBeChecked).isPresent();
            case 2 -> sellerAccountRepository.findByPhoneNumber(toBeChecked).isPresent();
            default -> false;
        };
    }


    public boolean checkPasswordValidation(String nationalId, String password) {
        Optional<Seller> seller = sellerAccountRepository.findById(nationalId);
        return ProjectInternalTools.passwordEncryptor.encryptPassword(password).equals(seller.get().getEncryptedPassword());
    }


    public Seller getSellerWithNationalId(String nationalId) {
        return sellerAccountRepository.findById(nationalId).get();
    }


//    public Seller getSellerWithEmail(String email) {
//        return sellerAccountRepository.findSellerByEmail(email).get();
//    }
//
//    public boolean checkIfSellerExistedWithThisEmail(String email) {
//        Optional<Seller> seller = sellerAccountRepository.findSellerByEmail(email);
//        return seller.isPresent();
//    }
//
//    public boolean checkPasswordValidationWithEmail(String email, String password) {
//        Optional<Seller> seller = sellerAccountRepository.findSellerByEmail(email);
//        return seller.get().getEncryptedPassword().equals(ProjectInternalTools.passwordEncryptor.encryptPassword(password));
//    }
//
//    public boolean checkIfSellerExistedWithThisPhoneNumber(String phonenumber) {
//        return sellerAccountRepository.findSellerByPhoneNumber(ProjectInternalTools.trimPhoneNumber(phonenumber)).isPresent();
//    }
//
//    public boolean checkPasswordValidationWithPhoneNumber(String phonenumber, String password) {
//        Optional<Seller> seller = sellerAccountRepository.findSellerByPhoneNumber(ProjectInternalTools.trimPhoneNumber(phonenumber));
//        return seller.get().getEncryptedPassword().equals(ProjectInternalTools.passwordEncryptor.encryptPassword(password));
//    }
//
//    public Seller getSellerWithPhoneNumber(String phonenumber) {
//        return sellerAccountRepository.findSellerByPhoneNumber(ProjectInternalTools.trimPhoneNumber(phonenumber)).get();
//    }
//
//    public String checkLoginInputType(String input) {
//        if (input.matches("(?<!\\d)(0\\d{10}|9\\d{11}|[1-9]\\d{9})(?!\\d)")) {
//            logger.info("checkLoginInputType: input is phoneNumber");
//            return "phoneNumber";
//        } else if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
//            logger.info("checkLoginInputType: input is email address");
//            return "email";
//        } else {
//            logger.info("checkLoginInputType: input is invalid");
//            return "invalid";
//        }
//    }

    public String login(String nationalId) {
        Seller seller = sellerAccountRepository.findByNationalId(nationalId).get();
        seller.setLastLoginDate(new Date(System.currentTimeMillis()));
        String token = TokenService.generateToken();
        sellerAccountRepository.save(seller);
        return token;
    }

    @Transactional
    public void saveToken(String nationalId, String token) {
        Optional<Seller> seller = sellerAccountRepository.findByNationalId(nationalId);
        if (seller.isPresent()) {
            String sellerNationalId = seller.get().getNationalId();
            TokenCookie tokenCookie = new TokenCookie();
            tokenCookie.setId((long) -1);
            tokenCookie.setToken(token);
            tokenCookie.setUserNationalId(sellerNationalId);
            tokenCookieRepository.save(tokenCookie);
        }
    }

    public Seller findSellerByToken(String token) {
        Optional<TokenCookie> tokenCookie = tokenCookieRepository.findByToken(token);
        return tokenCookie.map(cookie -> sellerAccountRepository.findByNationalId(cookie.getUserNationalId()).get()).orElse(null);
    }

    public boolean checkSellerAccountInfo(String nationalId) {
        Seller seller = sellerAccountRepository.findByNationalId(nationalId).get();
        if (seller.getFirstName() == null || seller.getLastName() == null )
//        || seller.getEmail() == null || !seller.isPhoneVerified() || !seller.isEmailVerified() || seller.getStoreId() == 0)
            return false;
        return true;
    }
}
