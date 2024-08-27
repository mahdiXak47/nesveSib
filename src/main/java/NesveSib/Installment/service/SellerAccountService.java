package NesveSib.Installment.service;


import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.respository.SellerAccountRepository;
import NesveSib.Installment.security.PasswordEncryptor;
import NesveSib.Installment.utils.ProjectInternalTools;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerAccountService {

    private final SellerAccountRepository sellerAccountRepository;

    public SellerAccountService(SellerAccountRepository sellerAccountRepository, PasswordEncryptor passwordEncryptor) {
        this.sellerAccountRepository = sellerAccountRepository;
    }





    public void createSellerAccount(Seller seller) {
        seller.setEncryptedPassword(ProjectInternalTools.passwordEncryptor.encryptPassword(seller.getEncryptedPassword()));
        sellerAccountRepository.save(seller);
    }

    public boolean checkIfSellerExisted(String nationalId) {
        return sellerAccountRepository.existsById(nationalId);
    }

    public boolean checkPasswordValidation(String nationalId,String password) {
        Optional<Seller> seller = sellerAccountRepository.findById(nationalId);
        return ProjectInternalTools.passwordEncryptor.encryptPassword(password).equals(seller.get().getEncryptedPassword());
    }
}
