package NesveSib.Installment.controller;

import NesveSib.Installment.model.SimpleUser;
import NesveSib.Installment.service.SimpleUserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleController {
    private final SimpleUserService simpleService;

    public SimpleController(SimpleUserService simpleService) {
        this.simpleService = simpleService;
    }

    @PostMapping("/signup")
    public SimpleUser signUp(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam String password) {
        return simpleService.signUp(firstName, lastName, username, password);
    }

    @PostMapping("/login")
    public SimpleUser login(
            @RequestParam String username,
            @RequestParam String password) {
        return simpleService.login(username, password);
    }
}