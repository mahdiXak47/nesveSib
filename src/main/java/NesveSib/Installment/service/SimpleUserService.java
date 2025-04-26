package NesveSib.Installment.service;

import NesveSib.Installment.model.SimpleUser;
import NesveSib.Installment.repository.SimpleUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService {

    private final SimpleUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SimpleUserService(SimpleUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SimpleUser signUp(String firstName, String lastName, String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        SimpleUser user = new SimpleUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public SimpleUser login(String username, String password) {
        SimpleUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}