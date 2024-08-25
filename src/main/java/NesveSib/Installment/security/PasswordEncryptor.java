package NesveSib.Installment.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptor {

    public String encryptPassword(String password) {
        String salt = "securewithrandombullshit";
        return BCrypt.hashpw(password, BCrypt.gensalt(12) + salt);
    }
}
