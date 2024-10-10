package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.addingProductToStore.PhoneAddedToStore;
import NesveSib.Installment.service.SellerDashboardService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller-dashboard")
public class SellerDashboardController {


    private final Logger logger = ProjectInternalTools.getLogger(SellerDashboardController.class.getName());
    private final SellerDashboardService sellerDashboardService;

    public SellerDashboardController(SellerDashboardService sellerDashboardService) {
        this.sellerDashboardService = sellerDashboardService;
    }


    @PostMapping("/add_new_phone_to_storage")
    private ResponseEntity<String> addNewPhoneToStorage(@RequestBody PhoneAddedToStore newPhone) {
        if (sellerDashboardService.addingNewPhoneToStoreStorage(newPhone)) {
            logger.info("addNewPhoneToStorage phone added to seller storage successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        }
        else {
            logger.info("addNewPhoneToStorage oops error!");
            return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
        }
    }

}
