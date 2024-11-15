package NesveSib.Installment.service;

import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.requestInputs.NewUserRequestInput;
import NesveSib.Installment.respository.CustomerAccountRepository;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static NesveSib.Installment.utils.ProjectInternalTools.passwordEncryptor;
import static NesveSib.Installment.utils.ProjectInternalTools.trimPhoneNumber;

@Service
public class CustomerAccountService {

    private final Logger logger = ProjectInternalTools.getLogger(CustomerAccountService.class.getName());

    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }


    public boolean checkIfCustomerExisted(String toBeChecked, int type) {
        return switch (type) {
            case 1 -> customerAccountRepository.findByNationalId(toBeChecked).isPresent();
            case 2 -> customerAccountRepository.findByPhoneNumber(toBeChecked).isPresent();
            default -> false;
        };
    }


    public void createCustomerAccount(NewUserRequestInput input) {
//        Customer customer = new Customer(input.getNationalId(),input.getFathersName(),input.getFirstName(),input.getLastName(),
//                input.getEmail(),false,input.getAddress(),input.getPhoneNumber(),false,input.password(),
//                input.getDateOfBirth(),input.getFirstRelativePhoneNumber(),input.getSecondRelativePhoneNumber(), input.getThirdRelativePhoneNumber());

//        customer.setRelativePhoneNumber(trimPhoneNumber(customer.getRelativePhoneNumber()));
//        customer.setSecondRelativePhoneNumber(trimPhoneNumber(customer.getSecondRelativePhoneNumber()));
//        customer.setThirdRelativePhoneNumber(trimPhoneNumber(customer.getThirdRelativePhoneNumber()));

    }

    public boolean checkPasswordValidation(String nationalId, String password) {
        Optional<Customer> customer = customerAccountRepository.findById(nationalId);
        return passwordEncryptor.encryptPassword(password).equals(customer.get().getEncryptedPassword());
    }

    public void createRawCustomerAccount(NewUserRequestInput input) {
        Customer customer = new Customer(input.national_number(), input.phone_number(), input.password());
        customer.setEncryptedPassword(passwordEncryptor.encryptPassword(customer.getEncryptedPassword()));
        customer.setPhoneNumber(trimPhoneNumber(input.phone_number()));
        customerAccountRepository.save(customer);
        logger.info("createCustomerAccount: Customer account created");
    }
}
