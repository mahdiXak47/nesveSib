package NesveSib.Installment.conttroller;

import NesveSib.Installment.model.users.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer-panel")
public class CustomerPanelController {

    @PostMapping("/register")
    public void signingCustomer(@RequestBody Customer customer) {
        System.out.println(customer.toString());
        //TODO: signing customer to panel
    }

    @GetMapping("/login")
    public void loginCustomer(@RequestParam String username, @RequestParam String password) {
        System.out.println(username);
        System.out.println(password);
        //TODO: login customer to panel
    }



    @GetMapping("/login")
    public void loginCustomer(@RequestParam String emailOrPhoneNumber) {
        //TODO: check the input is phone number or email address
        //TODO: check the first part of the phoneNumber and parse the final phone number
        System.out.println(emailOrPhoneNumber);
        //TODO: 2 factor authorization logic for login with email
        //TODO: login customer to panel
    }



    /*TODO:
    *  - editing customer information
    *  - delete existed customer
    *  - buy a product with installments as a valid customer
    *  - settlement a product as a valid customer*/
}
