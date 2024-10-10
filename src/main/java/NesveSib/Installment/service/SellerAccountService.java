package NesveSib.Installment.service;


import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.requestInputs.LoginRequestCheckInput;
import NesveSib.Installment.requestInputs.NewSellerRequestInput;
import NesveSib.Installment.respository.SellerAccountRepository;
import NesveSib.Installment.security.PasswordEncryptor;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerAccountService {

    private final Logger logger = ProjectInternalTools.getLogger(SellerAccountService.class.getName());

    private final SellerAccountRepository sellerAccountRepository;

    public SellerAccountService(SellerAccountRepository sellerAccountRepository, PasswordEncryptor passwordEncryptor) {
        this.sellerAccountRepository = sellerAccountRepository;
    }

    public void createSellerAccount(NewSellerRequestInput input) {
        Seller seller = new Seller(input.getFirstName(),input.getLastName(),input.getUsername(),
                input.getNationalId(),input.getPhoneNumber(),false,input.getEmail(),false,
                input.getStoreAddress(),input.getPassword(),input.getStorePlate(),input.getStoreName());

        seller.setPhoneNumber(ProjectInternalTools.trimPhoneNumber(seller.getPhoneNumber()));
        seller.setEncryptedPassword(ProjectInternalTools.passwordEncryptor.encryptPassword(seller.getEncryptedPassword()));
        sellerAccountRepository.save(seller);
        logger.info("createSellerAccount: seller added to database successfully!");
    }

    public boolean checkIfSellerExisted(String nationalId) {
        return sellerAccountRepository.existsById(nationalId);
    }

    public boolean checkPasswordValidation(String nationalId,String password) {
        Optional<Seller> seller = sellerAccountRepository.findById(nationalId);
        return ProjectInternalTools.passwordEncryptor.encryptPassword(password).equals(seller.get().getEncryptedPassword());
    }

    public boolean checkIfSellerExistedWithThisUsername(String username) {
        Optional<Seller> seller = sellerAccountRepository.findSellerByUsername(username);
        return seller.isPresent();
    }

    public boolean checkPasswordValidationWithUsername(String username, String password) {
        Optional<Seller> seller = sellerAccountRepository.findSellerByUsername(username);
        return seller.get().getEncryptedPassword().equals(ProjectInternalTools.passwordEncryptor.encryptPassword(password));
    }

    public Seller getSellerWithNationalId(String nationalId) {
        return sellerAccountRepository.findById(nationalId).get();
    }

    public Seller getSellerWithUsername(String username) {
        return sellerAccountRepository.findSellerByUsername(username).get();
    }

    public Seller getSellerWithEmail(String email) {
        return sellerAccountRepository.findSellerByEmail(email).get();
    }

    public boolean checkIfSellerExistedWithThisEmail(String email) {
        Optional<Seller> seller = sellerAccountRepository.findSellerByEmail(email);
        return seller.isPresent();
    }

    public boolean checkPasswordValidationWithEmail(String email, String password) {
        Optional<Seller> seller = sellerAccountRepository.findSellerByEmail(email);
        return seller.get().getEncryptedPassword().equals(ProjectInternalTools.passwordEncryptor.encryptPassword(password));
    }

    public boolean checkIfSellerExistedWithThisPhoneNumber(String phonenumber) {
        return  sellerAccountRepository.findSellerByPhoneNumber(ProjectInternalTools.trimPhoneNumber(phonenumber)).isPresent();
    }

    public boolean checkPasswordValidationWithPhoneNumber(String phonenumber, String password) {
        Optional<Seller> seller = sellerAccountRepository.findSellerByPhoneNumber(ProjectInternalTools.trimPhoneNumber(phonenumber));
        return seller.get().getEncryptedPassword().equals(ProjectInternalTools.passwordEncryptor.encryptPassword(password));
    }

    public Seller getSellerWithPhoneNumber(String phonenumber) {
        return sellerAccountRepository.findSellerByPhoneNumber(ProjectInternalTools.trimPhoneNumber(phonenumber)).get();
    }

    public String checkLoginInputType(String input) {
        if (input.matches("(?<!\\d)(0\\d{10}|9\\d{11}|[1-9]\\d{9})(?!\\d)")) {
            logger.info("checkLoginInputType: input is phoneNumber");
            return "phoneNumber";
        }
        else if (input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            logger.info("checkLoginInputType: input is email address");
            return "email";
        } else {
            logger.info("checkLoginInputType: input is invalid");
            return "invalid";
        }
    }
}
