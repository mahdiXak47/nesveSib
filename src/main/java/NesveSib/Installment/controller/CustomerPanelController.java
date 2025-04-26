package NesveSib.Installment.controller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.requestInputs.CompleteCustomerAccountForm;
import NesveSib.Installment.requestInputs.LoginRequestCheckInput;
import NesveSib.Installment.requestInputs.NewUserRequestInput;
import NesveSib.Installment.service.CustomerAccountService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static NesveSib.Installment.exceptions.OutputCode.ERROR_4005;

@RestController
@RequestMapping("/customer-panel")
public class CustomerPanelController {

    private final Logger logger = ProjectInternalTools.getLogger(CustomerPanelController.class.getName());

    private final CustomerAccountService customerAccountService;

    public CustomerPanelController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    private ResponseEntity<String> tokenValidationCheck(String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        Customer customer = customerAccountService.findCustomerByToken(token);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("token is valid");
    }

    @CrossOrigin(origins = "http://localhost:5173") // Adjust the origin as needed
    @PostMapping("/signing-new-customer")
    public ResponseEntity<String> signingNewCustomer(@RequestBody NewUserRequestInput customer) {
        logger.info("signingCustomer: going to sign in new customer");
        if (!customerAccountService.checkIfCustomerExisted(customer.national_number(), 1)) {
            if (!customerAccountService.checkIfCustomerExisted(ProjectInternalTools.trimPhoneNumber(customer.phone_number()), 2)) {
                customerAccountService.createRawCustomerAccount(customer);
                logger.info("signingCustomer: customer created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("signingCustomer: phone number already existed");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(OutputCode.ERROR_4004.getCodeMessage());
            }
        } else {
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

    @PostMapping("/complete-account-to-active")
    public ResponseEntity<String> completeAccountToActive(@CookieValue(name = "token") String token, @RequestBody CompleteCustomerAccountForm input) {
        logger.info("completeAccountToActive: going to active customer account");
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Customer customer = customerAccountService.findCustomerByToken(token);
            if (customerAccountService.checkIfEmailIsDuplicated(input.getEmail())) {
                if (customerAccountService.completeAndActiveAccount(customer, input)) {
                    logger.info("completeAccountToActive: customer activated successfully");
                    return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
                } else {
                    logger.info("completeAccountToActive oops error!");
                    return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
                }
            } else {
                logger.info("completeAccountToActive: another user is active with this email address");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ERROR_4005.getCodeMessage());
            }
        } else
            return tokenValidation;
    }

    @PostMapping("/login-after-sign-in")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public ResponseEntity<String> loginAfterSignIn(@RequestBody String nationalId) {
        nationalId = nationalId.substring(1, nationalId.length()-1);
        String token = customerAccountService.login(nationalId);
        if (!token.split(";")[0].equals("error")) {
            ResponseCookie springCookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .sameSite("None")
                    .maxAge(3600)
                    .build();
            customerAccountService.saveToken(nationalId, token);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, springCookie.toString());
            return ResponseEntity.ok().headers(headers)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error while login");
    }

    @PostMapping("/login-with-nationalId")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public ResponseEntity<String> loginCustomerWithNationalId(@RequestBody LoginRequestCheckInput input) {
        logger.info("loginCustomerWithNationalId: going to log in as a valid customer with national id {}", input.getNationalId());
        if (customerAccountService.checkIfCustomerExisted(input.getNationalId(), 1)) {
            if (customerAccountService.checkPasswordValidation(input.getNationalId(), input.getPassword())) {
                logger.info("loginCustomerWithNationalId: customer logged in successfully");
                String token = customerAccountService.login(input.getNationalId());
                if (!token.split(";")[0].equals("error")) {
                    ResponseCookie springCookie = ResponseCookie.from("token", token)
                            .httpOnly(true)
                            .secure(true)
                            .path("/")
                            .sameSite("None")
                            .maxAge(3600)
                            .build();
                    customerAccountService.saveToken(input.getNationalId(), token);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.SET_COOKIE, springCookie.toString());
                    return ResponseEntity.ok().headers(headers)
                            .build();
                }
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("loginCustomerWithNationalId: customer password validation failed, password is incorrect");
                return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
            }
        } else {
            logger.info("loginCustomerWithNationalId: customer with national id {} does not exist", input.getNationalId());
            return ResponseEntity.ok(OutputCode.ERROR_5002.getCodeMessage());
        }
    }

    @GetMapping("/fetch-info-from-token")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public ResponseEntity<Customer> fetchCustomerFromToken(@CookieValue(name = "token") String token) {
        Customer customer = customerAccountService.findCustomerByToken(token);
        logger.info("fetchCustomerFromToken: going to fetch customer from token");
        if (customer!=null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
