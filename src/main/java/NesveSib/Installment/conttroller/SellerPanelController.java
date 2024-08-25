package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.service.SellerAccountService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seller-panel")
public class SellerPanelController {

    private final SellerAccountService sellerAccountService;
    private final Logger logger = ProjectInternalTools.logger;

    public SellerPanelController(SellerAccountService sellerAccountService) {
        this.sellerAccountService = sellerAccountService;
    }


    @PostMapping("/new-seller")
    public ResponseEntity<OutputCode> createNewSellerAccount(@RequestBody Seller seller) {
        seller.setPhoneNumber(sellerAccountService.trimPhoneNumber(seller.getPhoneNumber()));
//        System.out.println(seller.toString());
        if (!sellerAccountService.checkIfSellerExisted(seller.getNationalId())) {
            sellerAccountService.createSellerAccount(seller);
            logger.info("seller created successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001);
        }
        else {
            logger.info("seller with national id {} exists", seller.getNationalId());
            return ResponseEntity.ok(OutputCode.ERROR_5001);
        }
//        return ResponseEntity.ok(OutputCode.SUCCESS_2001);
    }

    @GetMapping("/login")
    public void loginAsValidSeller() {
        //TODO: login as a seller
    }

    /*TODO:
    *  - edit seller information
    *  - delete existed seller
    *  - buying subscription for existed seller*/
}
