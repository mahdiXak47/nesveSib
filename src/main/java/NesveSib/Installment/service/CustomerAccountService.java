package NesveSib.Installment.service;

import NesveSib.Installment.model.TokenCookie;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.requestInputs.CompleteAccountForm;
import NesveSib.Installment.requestInputs.NewUserRequestInput;
import NesveSib.Installment.respository.CustomerAccountRepository;
import NesveSib.Installment.respository.TokenCookieRepository;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static NesveSib.Installment.utils.ProjectInternalTools.passwordEncryptor;
import static NesveSib.Installment.utils.ProjectInternalTools.trimPhoneNumber;

@Service
public class CustomerAccountService {

    private final Logger logger = ProjectInternalTools.getLogger(CustomerAccountService.class.getName());

    private final CustomerAccountRepository customerAccountRepository;
    private final TokenCookieRepository tokenCookieRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository, TokenCookieRepository tokenCookieRepository) {
        this.customerAccountRepository = customerAccountRepository;
        this.tokenCookieRepository = tokenCookieRepository;
    }


    public boolean checkIfCustomerExisted(String toBeChecked, int type) {
        return switch (type) {
            case 1 -> customerAccountRepository.findByNationalId(toBeChecked).isPresent();
            case 2 -> customerAccountRepository.findByPhoneNumber(toBeChecked).isPresent();
            default -> false;
        };
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

    public String login(String nationalId) {
        Customer customer = customerAccountRepository.findByNationalId(nationalId).get();
        customer.setLastLoginDate(new Date(System.currentTimeMillis()));
        String token = TokenService.generateToken();
        customerAccountRepository.save(customer);
        return token;
    }

    @Transactional
    public void saveToken(String nationalId, String token) {
        Optional<Customer> customer = customerAccountRepository.findByNationalId(nationalId);
        if (customer.isPresent()) {
            String sellerNationalId = customer.get().getNationalId();
            TokenCookie tokenCookie = new TokenCookie();
            tokenCookie.setId((long) -1);
            tokenCookie.setToken(token);
            tokenCookie.setUserNationalId(sellerNationalId);
            tokenCookieRepository.save(tokenCookie);
        }
    }

    public Customer findCustomerByToken(String token) {
        Optional<TokenCookie> tokenCookie = tokenCookieRepository.findByToken(token);
        return tokenCookie.map(cookie -> customerAccountRepository.findByNationalId(cookie.getUserNationalId()).get()).orElse(null);
    }

    public boolean completeAndActiveAccount(Customer customer, CompleteAccountForm input) {
        customer.setFirstName(input.getFirstName());
        customer.setLastName(input.getLastName());
        customer.setFathersName(input.getFathersName());
        customer.setEmail(input.getEmail());
        customer.setAddress(input.getAddress());
        customer.setDateOfBirth(dateInitialization(input.getDateOfBirth()));
        customer.setRelativePhoneNumber(input.getRelativeNumber1());
        customer.setSecondRelativePhoneNumber(input.getRelativeNumber2());
        if (input.getRelativeNumber3().matches("\\^\\d{11}\\$"))
            customer.setThirdRelativePhoneNumber(input.getRelativeNumber3());
        else
            customer.setThirdRelativePhoneNumber("nadarad");
        customer.setActive(true);
        customerAccountRepository.save(customer);
        return true;
    }

    private Date dateInitialization(String inputDate) {
        String englishDateStr = inputDate.replaceAll("۱", "1")
                .replaceAll("۲", "2")
                .replaceAll("۳", "3")
                .replaceAll("۴", "4")
                .replaceAll("۵", "5")
                .replaceAll("۶", "6")
                .replaceAll("۷", "7")
                .replaceAll("۸", "8")
                .replaceAll("۹", "9")
                .replaceAll("۰", "0");
        String[] parts = englishDateStr.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        LocalDate gregorianDate = LocalDate.of(year, month, day);
        Date date = Date.from(gregorianDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("local Date: " + gregorianDate);
        System.out.println("date: " + date);
        return date;
    }
}
