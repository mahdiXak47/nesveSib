package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.service.SellerAccountService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller-panel")
public class SellerPanelController {

    private final SellerAccountService sellerAccountService;
    private final Logger logger = ProjectInternalTools.logger;

    public SellerPanelController(SellerAccountService sellerAccountService) {
        this.sellerAccountService = sellerAccountService;
    }


    @PostMapping("/new-seller")
    public ResponseEntity<String> createNewSellerAccount(@RequestBody Seller seller) {
        seller.setPhoneNumber(ProjectInternalTools.trimPhoneNumber(seller.getPhoneNumber()));
//        System.out.println(seller.toString());
        if (!sellerAccountService.checkIfSellerExisted(seller.getNationalId())) {
            sellerAccountService.createSellerAccount(seller);
            logger.info("seller created successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        }
        else {
            logger.info("seller with national id {} exists", seller.getNationalId());
            return ResponseEntity.ok(OutputCode.ERROR_5001.getCodeMessage());
        }
    }

    @GetMapping("/login-with-username")
    public void loginAsValidSeller(@RequestParam String username, @RequestParam String password) {

    }

    @GetMapping("/login-with-nationalId")
    public ResponseEntity<String> loginAsValidSellerWithNationalId(@RequestParam String nationalId, @RequestParam String password) {
        logger.info("going to log in as a valid seller with national id {}", nationalId);
        if(sellerAccountService.checkIfSellerExisted(nationalId)) {
            if (sellerAccountService.checkPasswordValidation(nationalId,password)) {
                logger.info("seller logged in successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("seller password validation failed, password is incorrect");
                return ResponseEntity.ok(OutputCode.ERROR_5003.getCodeMessage());
            }
        } else {
            logger.info("seller with national id {} does not exist", nationalId);
            return ResponseEntity.ok(OutputCode.ERROR_5002.getCodeMessage());
        }
    }

    @DeleteMapping("/delete-existed-seller")
    @PreAuthorize("hasAnyAuthority('seller:write')")
    public ResponseEntity<String> deleteSellerAccount(@RequestParam String sellerId) {
        return ResponseEntity.ok(OutputCode.ERROR_9999.getCodeMessage());
    }


    /*TODO:
    *  - edit seller information
    *  - delete existed seller
    *  - buying subscription for existed seller*/
}
