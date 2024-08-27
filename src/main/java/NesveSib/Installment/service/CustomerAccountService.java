package NesveSib.Installment.service;

import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.respository.CustomerAccountRepository;
import NesveSib.Installment.utils.ProjectInternalTools;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerAccountService {


    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }


    public boolean checkIfCustomerExisted(String nationalId) {
        return customerAccountRepository.existsById(nationalId);
    }

    public void createCustomerAccount(Customer customer) {
        customer.setEncryptedPassword(ProjectInternalTools.passwordEncryptor.encryptPassword(customer.getEncryptedPassword()));
        customerAccountRepository.save(customer);
    }

    public boolean checkPasswordValidation(String nationalId, String password) {
        Optional<Customer> customer = customerAccountRepository.findById(nationalId);
        return ProjectInternalTools.passwordEncryptor.encryptPassword(password).equals(customer.get().getEncryptedPassword());
    }
}
