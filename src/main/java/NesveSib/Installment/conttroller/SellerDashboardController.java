package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.addingProductToStore.PhoneToBeAddedToStore;
import NesveSib.Installment.service.SellerDashboardService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller-dashboard")
public class SellerDashboardController {


    private final Logger logger = ProjectInternalTools.getLogger(SellerDashboardController.class.getName());
    private final SellerDashboardService sellerDashboardService;

    public SellerDashboardController(SellerDashboardService sellerDashboardService) {
        this.sellerDashboardService = sellerDashboardService;
    }


    @PostMapping("/add_new_phone_to_storage")
    private ResponseEntity<String> addNewPhoneToStorage(@RequestBody PhoneToBeAddedToStore newPhone) {
        if (sellerDashboardService.addingNewPhoneToStoreStorage(newPhone)) {
            logger.info("addNewPhoneToStorage phone added to seller storage successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        }
        else {
            logger.info("addNewPhoneToStorage oops error!");
            return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
        }
    }

    @GetMapping("/get_all_phone_stock")
    private ResponseEntity<String> getAllPhoneStock() {
        //TODO: getting the data from database
        return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
    }

    @GetMapping("/get_all_side_products_stock")
    private ResponseEntity<String> getAllSideProductsStock() {
        //TODO: getting the data from database
        return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
    }

    @GetMapping("/get_todays_all_installments")
    private ResponseEntity<String> getTodaysAllInstallments() {
        //TODO: getting the data from database
        return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
    }

    @GetMapping("/get_most_recent_customers")
    private ResponseEntity<String> getMostRecentCustomers() {
        //TODO: getting the data from database
        return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
    }

    @GetMapping("/get_most_recent_products_sales")
    private ResponseEntity<String> getMostRecentProductsSales() {
        //TODO: getting the data from database
        return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
    }

    @GetMapping("/get_all_seller_private_information")
    private ResponseEntity<String> getAllSellerPrivateInformation() {
        //TODO: getting the data from database
        return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
    }

    //todo: gereftane etelaat marboot be analys frooshgah

}
