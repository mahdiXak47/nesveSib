package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.users.Customer;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.model.users.SellerPackage;
import NesveSib.Installment.model.users.SellerStore;
import NesveSib.Installment.requestInputs.CompleteCustomerAccountForm;
import NesveSib.Installment.requestInputs.CompleteSellerAccountForm;
import NesveSib.Installment.requestInputs.LoginRequestCheckInput;
import NesveSib.Installment.requestInputs.NewUserRequestInput;
import NesveSib.Installment.service.SellerAccountService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static NesveSib.Installment.exceptions.OutputCode.*;

@RestController
@RequestMapping("/seller-panel")
public class SellerPanelController {

    private final SellerAccountService sellerAccountService;
    private final Logger logger = ProjectInternalTools.getLogger(SellerPanelController.class.getName());

    public SellerPanelController(SellerAccountService sellerAccountService) {
        this.sellerAccountService = sellerAccountService;
    }

    //todo: this function is copy and should refactor!
    private ResponseEntity<String> tokenValidationCheck(String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("token is valid");
    }

    @PostMapping("/new-seller")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> createNewSellerAccount(@RequestBody NewUserRequestInput seller) {
        logger.info("createNewSellerAccount: going to sign in new seller");
        if (!sellerAccountService.checkIfSellerExisted(seller.national_number(), 1)) {
            if (!sellerAccountService.checkIfSellerExisted(ProjectInternalTools.trimPhoneNumber(seller.phone_number()), 2)) {
                sellerAccountService.createRawSellerAccount(seller);
                logger.info("createNewSellerAccount: seller created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_2001.getCodeMessage());
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ERROR_5007.getCodeMessage());
        } else {
            logger.info("createNewSellerAccount: seller with national id {} exists", seller.national_number());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ERROR_5001.getCodeMessage());
        }
    }

    @PostMapping("/login-after-sign-in")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public ResponseEntity<String> loginAfterSignIn(@RequestBody String nationalId) {
        nationalId = nationalId.substring(1, nationalId.length()-1);
        String token = sellerAccountService.login(nationalId);
        if (!token.split(";")[0].equals("error")) {
            ResponseCookie springCookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .sameSite("None")
                    .maxAge(3600)
                    .build();
            sellerAccountService.saveToken(nationalId, token);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, springCookie.toString());
            return ResponseEntity.ok().headers(headers)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error while login");
    }

    @PostMapping("/login-with-nationalId")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public ResponseEntity<String> loginAsValidSellerWithNationalId(@RequestBody LoginRequestCheckInput input) {
        logger.info("loginAsValidSellerWithNationalId: going to log in as a valid seller with national id {}", input.getNationalId());
        if (sellerAccountService.checkIfSellerExisted(input.getNationalId(), 1)) {
            if (sellerAccountService.checkPasswordValidation(input.getNationalId(), input.getPassword())) {
                logger.info("loginAsValidSellerWithNationalId: seller logged in successfully");
                String token = sellerAccountService.login(input.getNationalId());
                if (!token.split(";")[0].equals("error")) {
                    ResponseCookie springCookie = ResponseCookie.from("token", token)
                            .httpOnly(true)
                            .secure(true)
                            .path("/")
                            .sameSite("None")
                            .maxAge(3600)
                            .build();
                    sellerAccountService.saveToken(input.getNationalId(), token);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add(HttpHeaders.SET_COOKIE, springCookie.toString());
                    return ResponseEntity.ok().headers(headers)
                            .build();
                }
            }
            logger.info("loginAsValidSellerWithNationalId: seller password validation failed, password is incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ERROR_5003.getCodeMessage());
        }
        logger.info("loginAsValidSellerWithNationalId: seller with national id {} does not exist", input.getNationalId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_5002.getCodeMessage());

    }


    @GetMapping("/check-seller-info")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public boolean checkSellerAccountInfo(@RequestParam String nationalId) {
        return sellerAccountService.checkSellerAccountInfo(nationalId);
    }


    @PostMapping("/complete-account-to-active")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    private ResponseEntity<String> completeAccountToActive(@CookieValue(name = "token") String token, @RequestBody CompleteSellerAccountForm input) {
        logger.info("completeAccountToActive: going to active seller account");
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerAccountService.checkIfEmailIsDuplicated(input.getEmail())) {
                if (sellerAccountService.completeAndActiveAccount(seller, input)) {
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

    @GetMapping("/fetch-info-from-token")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
    public ResponseEntity<SellerPackage> fetchSellerFromToken(@CookieValue(name = "token") String token) {
        Seller seller = sellerAccountService.findSellerByToken(token);
        SellerStore sellerStore = sellerAccountService.findSellerStoreBySellerNationalId(seller.getNationalId());
        logger.info("fetchSellerFromToken: going to fetch customer from token");
        if (seller!=null) {
            logger.info("fetchSellerFromToken: data fetched successfully!");
            return ResponseEntity.ok(new SellerPackage(seller,sellerStore));
        }
        logger.info("fetchSellerFromToken: seller or sellerStore not Found!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


//    @GetMapping("/login-with-email")
//    public ResponseEntity<String> loginAsValidSellerWithEmail(@RequestParam String email, @RequestParam String password) {
//        if (sellerAccountService.checkIfSellerExistedWithThisEmail(email)) {
//            if (sellerAccountService.checkPasswordValidationWithEmail(email, password)) {
//                logger.info("loginAsValidSellerWithEmail: seller logged in successfully");
//                Seller seller = sellerAccountService.getSellerWithEmail(email);
//                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
//            }
//            logger.info("loginAsValidSellerWithEmail: seller password validation failed, password is incorrect");
//            return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
//        }
//        logger.info("loginAsValidSellerWithEmail: seller with email {} does not exist", email);
//        return ResponseEntity.ok(OutputCode.ERROR_5005.getCodeMessage());
//    }
//
//    @GetMapping("login-with-phonenumber")
//    public ResponseEntity<String> loginAsValidSellerWithPhonenumber(@RequestParam String phonenumber, @RequestParam String password) {
//        if (sellerAccountService.checkIfSellerExistedWithThisPhoneNumber(phonenumber)) {
//            if (sellerAccountService.checkPasswordValidationWithPhoneNumber(phonenumber, password)) {
//                logger.info("loginAsValidSellerWithPhonenumber: seller logged in successfully");
//                Seller seller = sellerAccountService.getSellerWithPhoneNumber(phonenumber);
//                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
//            }
//            logger.info("loginAsValidSellerWithPhonenumber: seller password validation failed, password is incorrect");
//            return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
//        }
//        logger.info("loginAsValidSellerWithPhonenumber: seller with phone number {} does not exist", phonenumber);
//        return ResponseEntity.ok(OutputCode.ERROR_5006.getCodeMessage());
//    }

//    @PostMapping("/login-request-check")
//    public ResponseEntity<String> loginRequestCheck(@RequestBody LoginRequestCheckInput input) {
//        logger.info("loginRequestCheck: going to login request check");
//        switch (sellerAccountService.checkLoginInputType(input.getInput())) {
//            case "email":
//                logger.info("loginRequestCheck: going to login with email address");
//                loginAsValidSellerWithEmail(input.getInput(), input.getPassword());
//                break;
//            case "phoneNumber":
//                logger.info("loginRequestCheck: going to login with phone number");
//                loginAsValidSellerWithPhonenumber(input.getInput(), input.getPassword());
//                break;
//            case "invalid":
//                logger.info("loginRequestCheck: input entered is invalid!");
//                return ResponseEntity.ok(OutputCode.ERROR_4008.getCodeMessage());
//        }
//        return ResponseEntity.ok("it will never reach here!");
//    }
//

    @DeleteMapping("/delete-existed-seller")
//    @PreAuthorize("hasAnyAuthority('seller:write')")
    public ResponseEntity<String> deleteSellerAccount(@RequestParam String sellerId) {
        System.out.println(sellerId);
        return ResponseEntity.ok(OutputCode.ERROR_9999.getCodeMessage());
    }


    /*TODO:
     *  - edit seller information
     *  - delete existed seller
     *  - buying subscription for existed seller*/
}
