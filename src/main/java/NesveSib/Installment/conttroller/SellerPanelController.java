package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.requestInputs.LoginRequestCheckInput;
import NesveSib.Installment.requestInputs.NewSellerRequestInput;
import NesveSib.Installment.service.SellerAccountService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
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
    public ResponseEntity<String> createNewSellerAccount(@RequestBody NewSellerRequestInput seller) {
        if (!sellerAccountService.checkIfSellerExisted(seller.getNationalId())) {
            sellerAccountService.createSellerAccount(seller);
            logger.info("createNewSellerAccount: seller created successfully");
            return ResponseEntity.ok(SUCCESS_2001.getCodeMessage());
        } else {
            logger.info("createNewSellerAccount: seller with national id {} exists", seller.getNationalId());
            return ResponseEntity.ok(ERROR_5001.getCodeMessage());
        }
    }

    @GetMapping("/login-with-username")
    public ResponseEntity<String> loginAsValidSeller(@RequestParam String username, @RequestParam String password) {
        if (sellerAccountService.checkIfSellerExistedWithThisUsername(username)) {
            if (sellerAccountService.checkPasswordValidationWithUsername(username, password)) {
                logger.info("loginAsValidSeller: seller with username {} and password {} completed!", username, password);
                //TODO: logged in as a seller completed!;
                Seller seller = sellerAccountService.getSellerWithUsername(username);
                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
            }
            logger.info("loginAsValidSeller: seller password is not correct!");
            return ResponseEntity.ok(ERROR_5003.name());
        }
        logger.info("loginAsValidSeller: seller with username {} does not exist!", username);
        return ResponseEntity.ok(ERROR_5004.getCodeMessage());
    }

    @GetMapping("/login-with-nationalId")
    public ResponseEntity<String> loginAsValidSellerWithNationalId(@RequestParam String nationalId, @RequestParam String password) {
        logger.info("loginAsValidSellerWithNationalId: going to log in as a valid seller with national id {}", nationalId);
        if (sellerAccountService.checkIfSellerExisted(nationalId)) {
            if (sellerAccountService.checkPasswordValidation(nationalId, password)) {
                logger.info("loginAsValidSellerWithNationalId: seller logged in successfully");
                Seller seller = sellerAccountService.getSellerWithNationalId(nationalId);
                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
            }
            logger.info("loginAsValidSellerWithNationalId: seller password validation failed, password is incorrect");
            return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
        }
        logger.info("loginAsValidSellerWithNationalId: seller with national id {} does not exist", nationalId);
        return ResponseEntity.ok(OutputCode.ERROR_5002.getCodeMessage());

    }

    @GetMapping("/login-with-email")
    public ResponseEntity<String> loginAsValidSellerWithEmail(@RequestParam String email, @RequestParam String password) {
        if (sellerAccountService.checkIfSellerExistedWithThisEmail(email)) {
            if (sellerAccountService.checkPasswordValidationWithEmail(email, password)) {
                logger.info("loginAsValidSellerWithEmail: seller logged in successfully");
                Seller seller = sellerAccountService.getSellerWithEmail(email);
                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
            }
            logger.info("loginAsValidSellerWithEmail: seller password validation failed, password is incorrect");
            return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
        }
        logger.info("loginAsValidSellerWithEmail: seller with email {} does not exist", email);
        return ResponseEntity.ok(OutputCode.ERROR_5005.getCodeMessage());
    }

    @GetMapping("login-with-phonenumber")
    public ResponseEntity<String> loginAsValidSellerWithPhonenumber(@RequestParam String phonenumber, @RequestParam String password) {
        if (sellerAccountService.checkIfSellerExistedWithThisPhoneNumber(phonenumber)) {
            if (sellerAccountService.checkPasswordValidationWithPhoneNumber(phonenumber, password)) {
                logger.info("loginAsValidSellerWithPhonenumber: seller logged in successfully");
                Seller seller = sellerAccountService.getSellerWithPhoneNumber(phonenumber);
                return ResponseEntity.ok(SUCCESS_2001.getCodeMessage() + "\n" + seller.toString());
            }
            logger.info("loginAsValidSellerWithPhonenumber: seller password validation failed, password is incorrect");
            return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
        }
        logger.info("loginAsValidSellerWithPhonenumber: seller with phone number {} does not exist", phonenumber);
        return ResponseEntity.ok(OutputCode.ERROR_5006.getCodeMessage());
    }

    @PostMapping("/login-request-check")
    public ResponseEntity<String> loginRequestCheck(@RequestBody LoginRequestCheckInput input) {
        logger.info("loginRequestCheck: going to login request check");
        switch (sellerAccountService.checkLoginInputType(input.getInput())) {
            case "email":
                logger.info("loginRequestCheck: going to login with email address");
                loginAsValidSellerWithEmail(input.getInput(), input.getPassword());
                break;
            case "phoneNumber":
                logger.info("loginRequestCheck: going to login with phone number");
                loginAsValidSellerWithPhonenumber(input.getInput(), input.getPassword());
                break;
            case "invalid":
                logger.info("loginRequestCheck: input entered is invalid!");
                return ResponseEntity.ok(OutputCode.ERROR_4004.getCodeMessage());
        }
        return ResponseEntity.ok("it will never reach here!");
    }


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
