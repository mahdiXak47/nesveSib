package NesveSib.Installment.service;


import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.respository.SellerAccountRepository;
import NesveSib.Installment.security.PasswordEncryptor;
import org.springframework.stereotype.Service;

@Service
public class SellerAccountService {

    private final SellerAccountRepository sellerAccountRepository;
    private final PasswordEncryptor passwordEncryptor;

    public SellerAccountService(SellerAccountRepository sellerAccountRepository, PasswordEncryptor passwordEncryptor) {
        this.sellerAccountRepository = sellerAccountRepository;
        this.passwordEncryptor = passwordEncryptor;
    }


    public String trimPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("09"))
            return phoneNumber.substring(1);
        else if (phoneNumber.startsWith("98"))
            return phoneNumber.substring(2);
        return phoneNumber;
    }


    public void createSellerAccount(Seller seller) {
        seller.setEncryptedPassword(passwordEncryptor.encryptPassword(seller.getEncryptedPassword()));
        sellerAccountRepository.save(seller);
    }

    public boolean checkIfSellerExisted(String nationalId) {
        return sellerAccountRepository.existsById(nationalId);
    }

}
