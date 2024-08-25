package NesveSib.Installment.conttroller;

import NesveSib.Installment.model.users.Seller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seller-panel")
public class SellerPanelController {

    @PostMapping("/new-seller")
    public void createNewSellerAccount(@RequestBody Seller seller) {
        System.out.println(seller.toString());
        //TODO: creating new account as a seller (signing up)
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
