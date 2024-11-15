package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.requestInputs.NewUserRequestInput;
import NesveSib.Installment.service.CustomerAccountService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-panel")
public class CustomerPanelController {

    private final Logger logger = ProjectInternalTools.getLogger(CustomerPanelController.class.getName());

    private final CustomerAccountService customerAccountService;

    public CustomerPanelController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @CrossOrigin(origins = "http://localhost:5173") // Adjust the origin as needed
    @PostMapping("/signing-new-customer")
    public ResponseEntity<String> signingNewCustomer(@RequestBody NewUserRequestInput customer) {
        logger.info("signingCustomer: going to sign in new customer");
//        System.out.println(customer.toString());
        if (!customerAccountService.checkIfCustomerExisted(customer.national_number(),1)) {
            if (!customerAccountService.checkIfCustomerExisted(ProjectInternalTools.trimPhoneNumber(customer.phone_number()),2)) {
                customerAccountService.createRawCustomerAccount(customer);
                logger.info("signingCustomer: customer created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(OutputCode.SUCCESS_2001.getCodeMessage());
            }
            else {
                logger.info("signingCustomer: phone number already existed");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(OutputCode.ERROR_4004.getCodeMessage());
            }
        }
        else {
            logger.info("signingCustomer: customer with national id {} exists", customer.national_number());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(OutputCode.ERROR_5001.getCodeMessage());
        }
    }

    @GetMapping("/login-with-username")
    public void loginCustomerWithUsername(@RequestParam String username, @RequestParam String password) {
        logger.info("loginCustomerWithUsername: going to log in existed customer with its username: " + username);
        System.out.println(username);
        System.out.println(password);
        //TODO: login customer to panel
    }

    @GetMapping("/login-with-nationalId")
    public ResponseEntity<String> loginCustomerWithNationalId(@RequestParam String nationalId, @RequestParam String password) {
        logger.info("loginCustomerWithNationalId: going to log in as a valid customer with national id {}", nationalId);
        if(customerAccountService.checkIfCustomerExisted(nationalId,1)) {
            if (customerAccountService.checkPasswordValidation(nationalId,password)) {
                logger.info("loginCustomerWithNationalId: customer logged in successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("loginCustomerWithNationalId: customer password validation failed, password is incorrect");
                return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
            }
        } else {
            logger.info("loginCustomerWithNationalId: customer with national id {} does not exist", nationalId);
            return ResponseEntity.ok(OutputCode.ERROR_5002.getCodeMessage());
        }
    }


    @GetMapping("/login")
    public void loginCustomer(@RequestParam String emailOrPhoneNumber) {
        logger.info("going to log in existed customer with email or phonenumber: " + emailOrPhoneNumber);
        //TODO: check the input is phone number or email address
        //TODO: check the first part of the phoneNumber and parse the final phone number
        System.out.println(emailOrPhoneNumber);
        //TODO: 2 factor authorization logic for login with email
        //TODO: login customer to panel
    }



    /*TODO:
    *  - editing customer information
    *  - delete existed customer
    *  - buy a product with installments as a valid customer
    *  - settlement a product as a valid customer*/
}
