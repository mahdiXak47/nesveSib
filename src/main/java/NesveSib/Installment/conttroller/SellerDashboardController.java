package NesveSib.Installment.conttroller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.addingProductToStore.PhoneToBeAddedToStore;
import NesveSib.Installment.model.addingProductToStore.SideProductToBeAddedToStore;
import NesveSib.Installment.model.productModels.PhoneStock;
import NesveSib.Installment.model.productModels.SideProductStock;
import NesveSib.Installment.model.users.SellerStoreInvestor;
import NesveSib.Installment.model.users.Seller;
import NesveSib.Installment.model.users.ShopMan;
import NesveSib.Installment.service.SellerAccountService;
import NesveSib.Installment.service.SellerDashboardService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller-dashboard")
public class SellerDashboardController {


    private final Logger logger = ProjectInternalTools.getLogger(SellerDashboardController.class.getName());
    private final SellerDashboardService sellerDashboardService;
    private final SellerAccountService sellerAccountService;

    public SellerDashboardController(SellerDashboardService sellerDashboardService, SellerAccountService sellerAccountService) {
        this.sellerDashboardService = sellerDashboardService;
        this.sellerAccountService = sellerAccountService;
    }

    @PostMapping("/add_new_phone_to_storage")
    private ResponseEntity<String> addNewPhoneToStorage(@CookieValue(name = "token") String token, @RequestBody PhoneToBeAddedToStore newPhone) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        if (sellerDashboardService.addingNewPhoneToStoreStorage(newPhone, seller)) {
            logger.info("addNewPhoneToStorage phone added to seller storage successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        } else {
            logger.info("addNewPhoneToStorage oops error!");
            return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
        }
    }

    @GetMapping("/get_all_phone_stock")
    private ResponseEntity<List<PhoneStock>> getAllPhoneStock(@CookieValue(name = "token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<PhoneStock> phoneStocks = sellerDashboardService.getPhoneStockBySellerCode(seller);
        if (phoneStocks != null && !phoneStocks.isEmpty()) {
            logger.info("getAllPhoneStock phones received from database successfully");
            return ResponseEntity.ok(phoneStocks);
        } else {
            logger.info("getAllPhoneStock oops error!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add_new_side_product_to_storage")
    private ResponseEntity<String> addNewSideProductToStorage(@CookieValue(name = "token") String token, @RequestBody SideProductToBeAddedToStore newProduct) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        if (sellerDashboardService.addingNewSideProductToStoreStorage(newProduct, seller)) {
            logger.info("addNewSideProductToStorage phone added to seller storage successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        } else {
            logger.info("addNewSideProductToStorage oops error!");
            return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
        }
    }

    @GetMapping("/get_all_side_products_stock")
    private ResponseEntity<List<SideProductStock>> getAllSideProductsStock(@CookieValue(name = "token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<SideProductStock> phoneStocks = sellerDashboardService.getSideProductStockBySellerCode(seller);
        if (phoneStocks != null && !phoneStocks.isEmpty()) {
            logger.info("getAllSideProductsStock side products received from database successfully");
            return ResponseEntity.ok(phoneStocks);
        } else {
            logger.info("getAllSideProductsStock oops error!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/adding-investor-to-store")
    private ResponseEntity<String> addInvestorToStore(@CookieValue(name = "token") String token, @RequestBody AddInvestorForm newInvestor) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        if (sellerDashboardService.addingNewInvestorToStore(newInvestor, seller)) {
            logger.info("addInvestorToStore investor added to seller storage successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        } else {
            logger.info("addInvestorToStore oops error!");
            return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
        }
    }

    @PostMapping("/adding-shopman-to-store")
    private ResponseEntity<String> addShopManToStore(@CookieValue(name = "token") String token, @RequestBody AddShopManForm newShopMan) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
        if (sellerDashboardService.addingNewShopManToStore(newShopMan, seller)) {
            logger.info("addShopManToStore investor added to seller storage successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        } else {
            logger.info("addShopManToStore oops error!");
            return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
        }
    }

    @GetMapping("/get-store-shopman-info")
    private ResponseEntity<List<ShopMan>> getStoreShopManInfo(@CookieValue(name = "token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ShopMan> shopManList = sellerDashboardService.getStoreShopmansBySellerStoreCode(seller);
        if (shopManList != null && !shopManList.isEmpty()) {
            logger.info("getStoreShopManInfo side products received from database successfully");
            return ResponseEntity.ok(shopManList);
        } else {
            logger.info("getStoreShopManInfo oops error!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get-store-investors")
    private ResponseEntity<List<SellerStoreInvestor>> getStoreInvestors(@CookieValue(name = "token")String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<SellerStoreInvestor> sellerInvestors = sellerDashboardService.getInvestorsBySellerStoreCode(seller);
        if (sellerInvestors != null && !sellerInvestors.isEmpty()) {
            logger.info("getStoreInvestors side products received from database successfully");
            return ResponseEntity.ok(sellerInvestors);
        } else {
            logger.info("getStoreInvestors oops error!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
