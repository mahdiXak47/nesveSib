package NesveSib.Installment.controller;

import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.model.productModels.Airpod;
import NesveSib.Installment.model.addingProductToStore.SideProductToBeAddedToStore;
import NesveSib.Installment.model.productModels.Watch;
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

    @PostMapping("/add_new_phone_to_storage")
    private ResponseEntity<String> addNewPhoneToStorage(@CookieValue(name = "token") String token, @RequestBody PhoneStock newPhone) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerDashboardService.addingNewPhoneToStoreStorage(newPhone, seller.getStoreId())) {
                logger.info("addNewPhoneToStorage phone added to seller storage successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("addNewPhoneToStorage oops error!");
                return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
            }
        } else
            return tokenValidation;
    }

    @PostMapping("/add_new_watch_to_storage")
    private ResponseEntity<String> addWatchToStorage(@CookieValue(name = "token") String token, @RequestBody Watch newWatch) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerDashboardService.addingNewWatchToStore(newWatch, seller.getStoreId())) {
                logger.info("addWatchToStorage watch added to seller storage successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("addWatchToStorage oops error!");
                return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
            }
        } else
            return tokenValidation;
    }

    @PostMapping("/add_new_airpod_to_storage")
    private ResponseEntity<String> addNewAirpodToStorage(@CookieValue(name = "token") String token, @RequestBody Airpod newAirpod) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerDashboardService.addingNewAirpodToStoreStorage(newAirpod, seller)) {
                logger.info("addNewAirpodToStorage phone added to seller storage successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("addNewAirpodToStorage oops error!");
                return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
            }
        } else
            return tokenValidation;
    }

    @GetMapping("/get_all_watch_stock")
    private ResponseEntity<List<Watch>> getAllWatchStock(@CookieValue(name = "token") String token) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            List<Watch> watches = sellerDashboardService.getWatchStockBySellerCode(seller.getStoreId());
            if (watches != null && !watches.isEmpty()) {
                logger.info("getAllWatchStock airpods received from database successfully");
                return ResponseEntity.ok(watches);
            } else {
                logger.info("getAllWatchStock oops error!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/get_all_airpod_stock")
    private ResponseEntity<List<Airpod>> getAllAirpodStock(@CookieValue(name = "token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Seller seller = sellerAccountService.findSellerByToken(token);
        if (seller == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Airpod> airpodsList = sellerDashboardService.getAirpodStockBySellerCode(seller.getStoreId());
        if (airpodsList != null && !airpodsList.isEmpty()) {
            logger.info("getAllAirpodStock airpods received from database successfully");
            return ResponseEntity.ok(airpodsList);
        } else {
            logger.info("getAllAirpodStock oops error!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerDashboardService.addingNewSideProductToStoreStorage(newProduct, seller)) {
                logger.info("addNewSideProductToStorage phone added to seller storage successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("addNewSideProductToStorage oops error!");
                return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
            }
        } else
            return tokenValidation;
    }

    @GetMapping("/get_all_side_products_stock")
    private ResponseEntity<List<SideProductStock>> getAllSideProductsStock(@CookieValue(name = "token") String token) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            List<SideProductStock> phoneStocks = sellerDashboardService.getSideProductStockBySellerCode(seller);
            if (phoneStocks != null && !phoneStocks.isEmpty()) {
                logger.info("getAllSideProductsStock side products received from database successfully");
                return ResponseEntity.ok(phoneStocks);
            } else {
                logger.info("getAllSideProductsStock oops error!");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/adding-investor-to-store")
    private ResponseEntity<String> addInvestorToStore(@CookieValue(name = "token") String token, @RequestBody AddInvestorForm newInvestor) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerDashboardService.addingNewInvestorToStore(newInvestor, seller)) {
                logger.info("addInvestorToStore investor added to seller storage successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("addInvestorToStore oops error!");
                return ResponseEntity.ok(OutputCode.ERROR_8888.getCodeMessage());
            }
        } else
            return tokenValidation;
    }

    @PostMapping("/adding-shopman-to-store")
    private ResponseEntity<String> addShopManToStore(@CookieValue(name = "token") String token, @RequestBody AddShopManForm newShopMan) {
        ResponseEntity<String> tokenValidation = tokenValidationCheck(token);
        if (tokenValidation.equals(ResponseEntity.status(HttpStatus.CREATED).body("token is valid"))) {
            Seller seller = sellerAccountService.findSellerByToken(token);
            if (sellerDashboardService.addingNewShopManToStore(newShopMan, seller)) {
                logger.info("addShopManToStore shop man added to seller storage successfully");
                return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
            } else {
                logger.info("addShopManToStore user shop men list is full");
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Maximum number of shop men reached. You cannot add more.");
            }
        } else
            return tokenValidation;
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
            logger.info("no shopman found for this user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get-store-investors")
    private ResponseEntity<List<SellerStoreInvestor>> getStoreInvestors(@CookieValue(name = "token") String token) {
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
