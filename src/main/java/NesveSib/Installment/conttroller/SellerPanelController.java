package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.users.Seller;
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

    @PostMapping("/new-seller")
    @CrossOrigin(origins = "http://localhost:5173") // Adjust the origin as needed
    public ResponseEntity<String> createNewSellerAccount(@RequestBody NewUserRequestInput seller) {
        logger.info("createNewSellerAccount: going to sign in new seller");
        if (!sellerAccountService.checkIfSellerExisted(seller.national_number(), 1)) {
            if (!sellerAccountService.checkIfSellerExisted(ProjectInternalTools.trimPhoneNumber(seller.phone_number()), 2)) {
                sellerAccountService.createRawSellerAccount(seller);
                logger.info("createNewSellerAccount: seller created successfully");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_2001.getCodeMessage());
        } else {
            logger.info("createNewSellerAccount: seller with national id {} exists", seller.national_number());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ERROR_5001.getCodeMessage());
        }
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
//                Seller seller = sellerAccountService.getSellerWithNationalId(nationalId);
//                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
            }
            logger.info("loginAsValidSellerWithNationalId: seller password validation failed, password is incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ERROR_5003.getCodeMessage());
        }
        logger.info("loginAsValidSellerWithNationalId: seller with national id {} does not exist", input.getNationalId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR_5002.getCodeMessage());

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
